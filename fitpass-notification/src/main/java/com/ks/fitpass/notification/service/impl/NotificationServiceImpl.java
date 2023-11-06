package com.ks.fitpass.notification.service.impl;

import com.ks.fitpass.notification.entity.ResponseMessage;
import com.ks.fitpass.notification.entity.Notification;
import com.ks.fitpass.notification.repository.NotificationRepository;
import com.ks.fitpass.notification.service.NotificationService;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;
    private final SimpMessagingTemplate simpMessagingTemplate;

    public NotificationServiceImpl(NotificationRepository notificationRepository, SimpMessagingTemplate simpMessagingTemplate) {
        this.notificationRepository = notificationRepository;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @Override
    public void sendPrivateNotification(final String userId) {
        ResponseMessage message = new ResponseMessage("Private Notification");

        simpMessagingTemplate.convertAndSendToUser(userId,"/all/private-notifications", message);
    }

    @Override
    public void sendGlobalNotification() {
        ResponseMessage message = new ResponseMessage("Global Notification");

        simpMessagingTemplate.convertAndSend("/all/global-notifications", message);
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
    public List<Notification> getAllConfirmCheckOutSuccessByEmpIdReceive(int empIdReceive) {
        return notificationRepository.getAllConfirmCheckOutSuccessByEmpIdReceive(empIdReceive);
    }

    @Override
    public List<Notification> getAllConfirmCheckInSuccessByEmpIdReceive(int empIdReceive) {
        return notificationRepository.getAllConfirmCheckInSuccessByEmpIdReceive(empIdReceive);
    }

    @Override
    public List<Notification> getAllConfirmCheckInCancelByEmpIdReceive(int empIdReceive) {
        return notificationRepository.getAllConfirmCheckInCancelByEmpIdReceive(empIdReceive);
    }

    @Override
    public List<Notification> getAllConfirmCheckOutCancelByEmpIdReceive(int empIdReceive) {
        return notificationRepository.getAllConfirmCheckOutCancelByEmpIdReceive(empIdReceive);
    }
}
