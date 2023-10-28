package com.ks.fitpass.notification.service;

import com.ks.fitpass.notification.entity.Notification;

public interface NotificationService {
    int insertNotification(Notification notification);
    Notification getConfirmCheckInByUserIdReceive(int userIdReceive);
    Notification getConfirmCheckOutByUserIdReceive(int userIdReceive);
    int updateStatusNotificationById(int notificationId, int status);
    Notification getConfirmCheckInByEmpIdReceive(int empIdReceive);
    Notification getConfirmCheckOutByEmpIdReceive(int empIdReceive);

}
