package com.ks.fitpass.web.controller;

import com.ks.fitpass.brand.dto.BrandPagnition;
import com.ks.fitpass.core.entity.User;
import com.ks.fitpass.core.repository.UserRepository;
import com.ks.fitpass.department.dto.DepartmentDTO;
import com.ks.fitpass.department.service.DepartmentService;
import com.ks.fitpass.brand.service.*;
import com.ks.fitpass.brand.entity.*;
import com.ks.fitpass.transaction.dto.TransactionDTO;
import com.ks.fitpass.transaction.service.TransactionService;
import com.ks.fitpass.wallet.service.WalletService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        // Get list of departments, default sorted by rating
        List<DepartmentDTO> departmentDTOList = departmentService.getAllDepartmentTopRatingForHome(1, 5);

        double credit = walletService.getBalanceByUserId(user.getUserId());
        session.setAttribute("userCredit", credit);

//        model.addAttribute("brands", brandList);
//        model.addAttribute("brandDepartmentsMap", brandDepartmentsMap);
        model.addAttribute("departments", departmentDTOList);
        session.setAttribute("userInfo", user);
        return "homepage/homepage-user";
    }

    @GetMapping("/homepage/brand")
    public ResponseEntity<BrandPagnition> getBrandWithPagnition(@RequestParam(defaultValue = "1") int page,
                                                                @RequestParam(defaultValue = "2") int size,
                                                                @RequestParam(required = false) String sortPrice,
                                                                @RequestParam(required = false) String sortRating) {
        List<Brand> brandList = brandService.getAllByStatus(1, page, size, sortPrice, sortRating);

        int totalBrands = brandService.countAllBrands(1, sortRating);
        int totalPages = (int) Math.ceil((double) totalBrands / size);

        Map<Integer, List<DepartmentDTO>> brandDepartmentsMap = new HashMap<>();
        for (Brand brand : brandList) {
            List<DepartmentDTO> departmentList = departmentService.getAllDepartmentByBrandId(brand.getBrandId(), 1, 5);
            brandDepartmentsMap.put(brand.getBrandId(), departmentList);
        }

        BrandPagnition brandPagnition = new BrandPagnition();
        brandPagnition.setListBrand(brandList);
        brandPagnition.setTotalPage(totalPages);
        brandPagnition.setBrandDepartmentsMap(brandDepartmentsMap);
        return ResponseEntity.ok(brandPagnition);
    }

    @PostMapping("/homepage")
    public String getNearByDepartmentList(@RequestParam("userLatitude") double userLatitude,
                                          @RequestParam("userLongitude") double userLongitude,
                                          @RequestParam(defaultValue = "1") int page,
                                          @RequestParam(defaultValue = "2") int size,
                                          Model model,
                                          @RequestParam(required = false) String city,
                                          @RequestParam(required = false) String sortPrice,
                                          @RequestParam(required = false) String sortRating) {
        Map<DepartmentDTO, Double> departmentDistanceMap = departmentService.getAllDepartmentByNearbyLocation(
                page, size, userLatitude, userLongitude, 10, city, sortPrice, sortRating);
        model.addAttribute("departmentDistanceMap",departmentDistanceMap);
        return "homepage/fragments/list-gym-fr";
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
