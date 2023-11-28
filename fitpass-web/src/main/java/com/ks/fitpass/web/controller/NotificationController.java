package com.ks.fitpass.web.controller;

import com.ks.fitpass.core.entity.User;
import com.ks.fitpass.notification.entity.Notification;
import com.ks.fitpass.notification.service.NotificationService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/notification")
public class NotificationController {

    private final Logger logger = LoggerFactory.getLogger(NotificationController.class);

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/seen")
    public ResponseEntity<Integer> performSeenNotification(@RequestParam("id") int notificationId) {
        int status = notificationService.updateStatusNotificationById(notificationId, 1);
        if (status > 0) {
            return ResponseEntity.ok(status);
        } else {
            return ResponseEntity.notFound().build(); // Status code 404 Not Found
        }
    }

    // Endpoint to get all notifications for a user
    @GetMapping("/user/all")
    public ResponseEntity<List<Notification>> getAllNotificationsForUser(HttpSession session) {
        User user = (User) session.getAttribute("userInfo");
        List<Notification> notifications = notificationService.getAllNotificationForUser(user.getUserId());
        return ResponseEntity.ok(notifications);
    }

    @GetMapping("/user/newest-unseen")
    public ResponseEntity<List<Notification>> get3NewestUnseenNotificationsForUser(HttpSession session) {
        User user = (User) session.getAttribute("userInfo");
        int userId = user.getUserId();
        List<Notification> notifications = notificationService.get3NewestUnseenNotificationForUser(userId);


        return ResponseEntity.ok(notifications);
    }

    // Endpoint to get all notifications for an employee
    @GetMapping("/employee/all")
    public ResponseEntity<List<Notification>> getAllNotificationsForEmployee(HttpSession session) {
        User user = (User) session.getAttribute("userInfo");
        List<Notification> notifications = notificationService.getAllNotificationForEmployee(user.getUserId());
        return ResponseEntity.ok(notifications);
    }
}
