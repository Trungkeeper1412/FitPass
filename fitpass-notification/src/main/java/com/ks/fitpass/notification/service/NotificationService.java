package com.ks.fitpass.notification.service;

import com.ks.fitpass.notification.entity.Notification;

import java.util.List;

public interface NotificationService {
    int insertNotification(Notification notification);

    int updateStatusNotificationById(int notificationId, int status);

    List<Notification> getAllNotificationForUser(int userIdReceive);

    List<Notification> get3NewestUnseenNotificationForUser(int userIdReceive);

    List<Notification> getAllNotificationForEmployee(int empIdReceive);
}
