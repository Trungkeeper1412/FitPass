package com.ks.fitpass.notification.repository;

import com.ks.fitpass.notification.entity.Notification;

import java.util.List;

public interface NotificationRepository {
    int insertNotification(Notification notification);

    // Lấy ra thông báo check in được gửi tới user
    Notification getConfirmCheckInByUserIdReceive(int userIdReceive);

    // Lấy ra thông báo check out được gửi tới user
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

    // Lấy ra thông báo người dùng không đủ số dư tới employee
}
