package com.ks.fitpass.web.controller;

import com.ks.fitpass.core.entity.User;
import com.ks.fitpass.core.repository.UserRepository;
import com.ks.fitpass.department.entity.ItemInventory;
import com.ks.fitpass.department.service.ItemInventoryService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;

@Controller
@RequestMapping("/item")
public class ItemInventoryController {
    private final ItemInventoryService itemInventoryService;
    private final UserRepository userRepository;

    public ItemInventoryController(ItemInventoryService itemInventoryService, UserRepository userRepository) {
        this.itemInventoryService = itemInventoryService;
        this.userRepository = userRepository;
    }

    @GetMapping("/add")
    public String addToInventory(@RequestParam int gymPlanId, HttpSession session) {
        User user = (User) session.getAttribute("userInfo");
        if(user == null) {

        }
        ItemInventory itemInventory = new ItemInventory();
        itemInventory.setUserId(user.getUserId());
        itemInventory.setPlanId(gymPlanId);
        itemInventory.setPlanActiveTime(Timestamp.valueOf("2023-10-17 00:00:00"));
        itemInventory.setItem_status_key(1);
        itemInventory.setPlanExpiredTime(Timestamp.valueOf("2023-10-31 23:59:59"));

        boolean check = itemInventoryService.checkDuplicateItemInventory(user.getUserId(), gymPlanId);

        if(!itemInventoryService.checkDuplicateItemInventory(user.getUserId(), gymPlanId)) {
            itemInventoryService.insert(itemInventory);
        }

        return "redirect:/cart/view";
    }

    @GetMapping("/view")
    public String showInventory(){
        return "inventory";
    }
}
