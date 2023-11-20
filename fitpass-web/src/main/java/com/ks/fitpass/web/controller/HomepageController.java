package com.ks.fitpass.web.controller;

import com.ks.fitpass.brand.dto.BrandPagnition;
import com.ks.fitpass.core.entity.User;
import com.ks.fitpass.core.repository.UserRepository;
import com.ks.fitpass.department.dto.DepartmentDTO;
import com.ks.fitpass.department.dto.DepartmentHomePagePagnition;
import com.ks.fitpass.department.entity.Department;
import com.ks.fitpass.department.service.DepartmentService;
import com.ks.fitpass.brand.service.*;
import com.ks.fitpass.brand.entity.*;
import com.ks.fitpass.transaction.dto.TransactionDTO;
import com.ks.fitpass.transaction.service.TransactionService;
import com.ks.fitpass.wallet.service.WalletService;
import com.stripe.param.tax.RegistrationCreateParams;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class HomepageController {
    private final UserRepository userRepository;
    private final DepartmentService departmentService;
    private final BrandService brandService;
    private final WalletService walletService;
    private final TransactionService transactionService;
    @GetMapping("/homepage")
    public String getHomepage(Principal principal, HttpSession session, Model model) {
        com.ks.fitpass.core.entity.User user = userRepository.findByAccount(principal.getName());

//        List<Brand> brandList = brandService.getAllByStatus(1, page, size);
//
//        // Create a map to store brand ID and corresponding department list
//        Map<Integer, List<DepartmentDTO>> brandDepartmentsMap = new HashMap<>();
//        for (Brand brand : brandList) {
//            List<DepartmentDTO> departmentList = departmentService.getAllDepartmentByBrandId(brand.getBrandId(), 1, 5);
//            brandDepartmentsMap.put(brand.getBrandId(), departmentList);
//        }

//        // Get list of departments, default sorted by rating
//        List<DepartmentDTO> departmentDTOList = departmentService.getAllDepartmentTopRatingForHome(1, 5);

        double credit = walletService.getBalanceByUserId(user.getUserId());
        session.setAttribute("userCredit", credit);

//        model.addAttribute("brands", brandList);
//        model.addAttribute("brandDepartmentsMap", brandDepartmentsMap);
//        model.addAttribute("departments", departmentDTOList);
        session.setAttribute("userInfo", user);
        return "homepage/homepage-user";
    }

   @GetMapping("/homepage/brand")
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
    }
    catch (IllegalArgumentException e) {
        // Log the exception for debugging purposes
        e.printStackTrace();
        // Return a BAD_REQUEST response for negative page number
        return ResponseEntity.badRequest().build();
    }
    catch (Exception e) {
        // Log the exception for debugging purposes
        e.printStackTrace();
        // Return an appropriate error response
        return ResponseEntity.status(500).build();
    }
}

    @PostMapping("/homepage")
    public ResponseEntity<DepartmentHomePagePagnition> getNearByDepartmentList(@RequestParam("userLatitude") double userLatitude,
                                                                               @RequestParam("userLongitude") double userLongitude,
                                                                               @RequestParam(defaultValue = "1") int page,
                                                                               @RequestParam(defaultValue = "2") int size,
                                                                               @RequestParam(required = false, defaultValue = "") String city,
                                                                               @RequestParam(required = false, defaultValue = "") String sortPrice,
                                                                               @RequestParam(required = false, defaultValue = "0") String sortRating,
                                                                               @RequestParam(required = false, defaultValue = "10") String belowDistance) {

        List<Department> departmentList = departmentService.getAllDepartmentByNearbyLocation(
                page, size, userLatitude, userLongitude,  city, sortPrice, sortRating, belowDistance);
        int totalDepartment = departmentService.countAllDepartment(1, city, sortPrice, sortRating, userLatitude, userLongitude, belowDistance);
        int totalPages = (int) Math.ceil((double) totalDepartment / size);
        int currentPage = page;

        DepartmentHomePagePagnition departmentHomePagePagnition = new DepartmentHomePagePagnition();
        departmentHomePagePagnition.setDepartmentList(departmentList);
        departmentHomePagePagnition.setTotalPage(totalPages);
        departmentHomePagePagnition.setCurrentPage(currentPage);

        return ResponseEntity.ok(departmentHomePagePagnition);
    }

    @GetMapping("/profile/calendar")
    public String getCalendar(){
        return "user/calendar";
    }


    @GetMapping("/profile/my-profile")
    public String showProfile(Model model, HttpSession session) {

        User user = (User) session.getAttribute("userInfo");
        List<TransactionDTO> transactionDTOList = transactionService.getListTransactionByUserId(user.getUserId());
        model.addAttribute("transactionList", transactionDTOList);

        return "user/profile";
    }
}
