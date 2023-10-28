package com.ks.fitpass.notification.service.impl;

import com.ks.fitpass.notification.entity.Notification;
import com.ks.fitpass.notification.repository.NotificationRepository;
import com.ks.fitpass.notification.service.NotificationService;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public int insertNotification(Notification notification) {
        return notificationRepository.insertNotification(notification);
    }

    @Override
    public Notification getConfirmCheckInByUserIdReceive(int userIdReceive) {
        return notificationRepository.getConfirmCheckInByUserIdReceive(userIdReceive);
    }

    @Override
    public Notification getConfirmCheckOutByUserIdReceive(int userIdReceive) {
        return notificationRepository.getConfirmCheckOutByUserIdReceive(userIdReceive);
    }

    @Override
    public int updateStatusNotificationById(int notificationId, int status) {
        return notificationRepository.updateStatusNotificationById(notificationId, status);
    }

    @Override
    public Notification getConfirmCheckInByEmpIdReceive(int empIdReceive) {
        return notificationRepository.getConfirmCheckInByEmpIdReceive(empIdReceive);
    }

    @Override
    public Notification getConfirmCheckOutByEmpIdReceive(int empIdReceive) {
        return notificationRepository.getConfirmCheckOutByEmpIdReceive(empIdReceive);
    }
}
