package com.ks.fitpass.web.controller;

import com.ks.fitpass.notification.entity.Notification;
import com.ks.fitpass.notification.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/notification")
public class NotificationController {

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
    @GetMapping("/user/all/{id}")
    public ResponseEntity<List<Notification>> getAllNotificationsForUser(@PathVariable int id) {
        List<Notification> notifications = notificationService.getAllNotificationForUser(id);
        return ResponseEntity.ok(notifications);
    }

    @GetMapping("/user/newest-unseen/{id}")
    public ResponseEntity<List<Notification>> get3NewestUnseenNotificationsForUser(@PathVariable int id) {
        List<Notification> notifications = notificationService.get3NewestUnseenNotificationForUser(id);
        return ResponseEntity.ok(notifications);
    }

    // Endpoint to get all notifications for an employee
    @GetMapping("/employee/all/{id}")
    public ResponseEntity<List<Notification>> getAllNotificationsForEmployee(@PathVariable int id) {
        List<Notification> notifications = notificationService.getAllNotificationForEmployee(id);
        return ResponseEntity.ok(notifications);
    }
}
