package com.ks.fitpass.web.controller;

import com.ks.fitpass.brand.dto.BrandPagnition;
import com.ks.fitpass.brand.dto.DepartmentBrandHomepageSearch;
import com.ks.fitpass.brand.entity.Brand;
import com.ks.fitpass.brand.service.BrandService;
import com.ks.fitpass.core.entity.User;
import com.ks.fitpass.department.dto.DepartmentDTO;
import com.ks.fitpass.department.dto.DepartmentHomePagePagnition;
import com.ks.fitpass.department.entity.Department;
import com.ks.fitpass.department.service.DepartmentService;
import com.ks.fitpass.wallet.service.WalletService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/homepage")
public class HomepageController {
    private final DepartmentService departmentService;
    private final BrandService brandService;
    private final WalletService walletService;

    @GetMapping("")
    public String getHomepage(HttpSession session) {
        User userSession = (User) session.getAttribute("userInfo");
        if (userSession != null) {
            double credit = walletService.getBalanceByUserId(userSession.getUserId());
            session.setAttribute("userCredit", credit);
        }
        return "homepage-user";
    }

    @GetMapping("/brand")
    public ResponseEntity<BrandPagnition> getBrandWithPagination(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "2") int size,
            @RequestParam(required = false) String sortPrice,
            @RequestParam(required = false) String sortRating) {
        try {
            List<Brand> brandList = brandService.getAllByStatus(1, page, size, sortPrice, sortRating);

            int totalBrands = brandService.countAllBrands(1, sortRating);
            int totalPages = (int) Math.ceil((double) totalBrands / size);
            int currentPage = page;

            Map<Integer, List<DepartmentDTO>> brandDepartmentsMap = new HashMap<>();
            for (Brand brand : brandList) {
                List<DepartmentDTO> departmentList = departmentService.getAllDepartmentByBrandId(brand.getBrandId(), 1, 5);
                brandDepartmentsMap.put(brand.getBrandId(), departmentList);
            }

            BrandPagnition brandPagnition = new BrandPagnition();
            brandPagnition.setListBrand(brandList);
            brandPagnition.setTotalPage(totalPages);
            brandPagnition.setCurrentPage(currentPage);
            brandPagnition.setBrandDepartmentsMap(brandDepartmentsMap);
            return ResponseEntity.ok(brandPagnition);
        } catch (IllegalArgumentException e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            // Return a BAD_REQUEST response for negative page number
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            // Return an appropriate error response
            return ResponseEntity.status(400).build();
        }
    }

    @PostMapping("")
    public ResponseEntity<DepartmentHomePagePagnition> getNearByDepartmentList(
            @RequestParam("userLatitude") double userLatitude,
            @RequestParam("userLongitude") double userLongitude,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "2") int size,
            @RequestParam(required = false, defaultValue = "") String city,
            @RequestParam(required = false, defaultValue = "") String sortPrice,
            @RequestParam(required = false, defaultValue = "0") String sortRating,
            @RequestParam(required = false, defaultValue = "10") String belowDistance) {

        try {
            validateInputParameters(page, size, userLatitude, userLongitude, belowDistance); // Updated method for input validation

            List<Department> departmentList = departmentService.getAllDepartmentByNearbyLocation(
                    page, size, userLatitude, userLongitude, city, sortPrice, sortRating, belowDistance);
            int totalDepartment = departmentService.countAllDepartment(1, city, sortPrice, sortRating, userLatitude, userLongitude, belowDistance);
            int totalPages = (int) Math.ceil((double) totalDepartment / size);
            int currentPage = page;

            DepartmentHomePagePagnition departmentHomePagePagnition = new DepartmentHomePagePagnition();
            departmentHomePagePagnition.setDepartmentList(departmentList);
            departmentHomePagePagnition.setTotalPage(totalPages);
            departmentHomePagePagnition.setCurrentPage(currentPage);

            return ResponseEntity.ok(departmentHomePagePagnition);

        } catch (IllegalArgumentException e) {
            // Handle bad requests
            return ResponseEntity.badRequest().build();

        } catch (Exception e) {
            // Handle internal server errors
            return ResponseEntity.status(500).build();
        }
    }

    private void validateInputParameters(int page, int size, double userLatitude, double userLongitude, String belowDistance) {
        if (page <= 0 || size <= 0) {
            throw new IllegalArgumentException("Page and size must be greater than zero.");
        }

        // Add additional validation for out-of-range values
        if (userLatitude < -90 || userLatitude > 90) {
            throw new IllegalArgumentException("Latitude must be between -90 and 90.");
        }

        if (userLongitude < -180 || userLongitude > 180) {
            throw new IllegalArgumentException("Longitude must be between -180 and 180.");
        }

        try {
            double distance = Double.parseDouble(belowDistance);
            if (distance < 0) {
                throw new IllegalArgumentException("Distance must be non-negative.");
            }
            // Add more specific range checks for distance if needed
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid distance format.");
        }
    }

    @GetMapping("/search")
    public String getSearchResult(Model model, @RequestParam("search") String search,
                                  @RequestParam(defaultValue = "1") int page,
                                  @RequestParam(defaultValue = "4") int size) {
        List<DepartmentBrandHomepageSearch> list = brandService.searchBrandWithPagnition(search, page, size);

        int totalResult = brandService.countSearchBrand(search);

        int totalPage = (int) Math.ceil((double) totalResult / size);

        int startPage = Math.max(1, page - 2);
        int endPage = Math.min(totalPage, page + 2);
        if (page <= 3) {
            endPage = Math.min(totalPage, 5);
        }
        if (page >= totalPage - 2) {
            startPage = Math.max(1, totalPage - 4);
        }

        model.addAttribute("search", search);
        model.addAttribute("list", list);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "search-gym-result";
    }
}