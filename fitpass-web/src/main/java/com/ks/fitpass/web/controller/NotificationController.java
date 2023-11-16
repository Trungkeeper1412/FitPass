package com.ks.fitpass.web.controller;

import com.ks.fitpass.notification.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/notification")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/seen")
    public ResponseEntity<Integer> performSeenNotification(@RequestParam("id") int notificationId) {
        // Chuyển status thành 1 là đã đọc
        int status = notificationService.updateStatusNotificationById(notificationId, 1);
        return ResponseEntity.ok(status);
    }
}
