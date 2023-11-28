package com.ks.fitpass.notification.repository;

import com.ks.fitpass.notification.entity.Notification;

import java.util.List;

public interface NotificationRepository {
    int insertNotification(Notification notification);

    int updateStatusNotificationById(int notificationId, int status);

    List<Notification> getAllNotificationForUser(int userIdReceive, int offset, int size);

    List<Notification> get3NewestUnseenNotificationForUser(int userIdReceive);

    List<Notification> getAllNotificationForEmployee(int empIdReceive);

    int getNumberOfUnseenNotification(int userIdReceive);

    int getTotalNotificationsForUser(int userId);
}
