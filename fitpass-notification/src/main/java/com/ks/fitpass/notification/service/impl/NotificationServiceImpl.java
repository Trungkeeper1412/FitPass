package com.ks.fitpass.notification.service.impl;

import com.ks.fitpass.notification.entity.Notification;
import com.ks.fitpass.notification.repository.NotificationRepository;
import com.ks.fitpass.notification.service.NotificationService;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public int updateStatusNotificationById(int notificationId, int status) {
        return notificationRepository.updateStatusNotificationById(notificationId, status);
    }

    @Override
    public int getNumberOfUnseenNotification(int userIdReceive) {
        return notificationRepository.getNumberOfUnseenNotification(userIdReceive);
    }

    @Override
    public int getTotalNotificationsForUser(int userId) {
        return notificationRepository.getTotalNotificationsForUser(userId);
    }

    @Override
    public List<Notification> getAllNotificationForUser(int userIdReceive, int page, int size) {
        int offset = (page - 1) * size;
        return notificationRepository.getAllNotificationForUser(userIdReceive, offset, size);
    }

    @Override
    public List<Notification> get3NewestUnseenNotificationForUser(int userIdReceive) {
        return notificationRepository.get3NewestUnseenNotificationForUser(userIdReceive);
    }

    @Override
    public List<Notification> getAllNotificationForEmployee(int empIdReceive) {
        return notificationRepository.getAllNotificationForEmployee(empIdReceive);
    }


}
