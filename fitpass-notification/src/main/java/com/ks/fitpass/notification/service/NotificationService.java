package com.ks.fitpass.notification.service;

import com.ks.fitpass.notification.entity.Notification;

import java.util.List;

public interface NotificationService {
    int insertNotification(Notification notification);
    Notification getConfirmCheckInByUserIdReceive(int userIdReceive);
    Notification getConfirmCheckOutByUserIdReceive(int userIdReceive);
    int updateStatusNotificationById(int notificationId, int status);

    // Lấy ra tất cả thông báo xác nhận thanh toán thành công tới employee
    List<Notification> getAllConfirmCheckOutSuccessByEmpIdReceive(int empIdReceive);

    // Lấy ra tất cả các thông báo xác nhận check in thành công tới employee
    List<Notification> getAllConfirmCheckInSuccessByEmpIdReceive(int empIdReceive);

    // Lấy ra tất cả các thông báo xác nhận hủy checkin tới employee
    List<Notification> getAllConfirmCheckInCancelByEmpIdReceive(int empIdReceive);

    // Lấy ra tất cả thông báo xác nhận hủy thanh toán tới employee
    List<Notification> getAllConfirmCheckOutCancelByEmpIdReceive(int empIdReceive);
}
