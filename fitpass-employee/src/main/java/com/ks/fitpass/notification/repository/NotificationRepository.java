package com.ks.fitpass.notification.repository;

import com.ks.fitpass.notification.entity.Notification;

public interface NotificationRepository {
    int insertNotification(Notification notification);
    Notification getConfirmCheckInByUserIdReceive(int userIdReceive);
    Notification getConfirmCheckInByEmpIdReceive(int empIdReceive);
    Notification getConfirmCheckOutByEmpIdReceive(int empIdReceive);
    Notification getConfirmCheckOutByUserIdReceive(int userIdReceive);
    int updateStatusNotificationById(int notificationId, int status);
}
