package com.ks.fitpass.notification.service.impl;

import com.ks.fitpass.notification.entity.Notification;
import com.ks.fitpass.notification.entity.ResponseMessage;
import com.ks.fitpass.notification.service.WebSocketService;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketServiceImpl implements WebSocketService {
    private final SimpMessagingTemplate simpMessagingTemplate;

    public WebSocketServiceImpl(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    public void notifyFrontend(Notification notification) {
        sendGlobalNotification();
        simpMessagingTemplate.convertAndSend("/all/messages", notification);
    }

    @Override
    public void notifyUser(int id, Notification notification) {
        sendPrivateNotification(String.valueOf(id));
        simpMessagingTemplate.convertAndSendToUser(String.valueOf(id), "/specific/private-messages", notification);
    }

    @Override
    public void notifyEmployee(int id, Notification notification) {
        sendPrivateNotification(String.valueOf(id));
        simpMessagingTemplate.convertAndSendToUser(String.valueOf(id), "/specific/private-response", notification);
    }

    @Override
    public void sendPrivateNotification(final String userId) {
        ResponseMessage message = new ResponseMessage("Private Notification");
        simpMessagingTemplate.convertAndSendToUser(userId, "/specific/private-notifications", message);
    }

    @Override
    public void sendGlobalNotification() {
        ResponseMessage message = new ResponseMessage("Global Notification");
        simpMessagingTemplate.convertAndSend("/all/global-notifications", message);
    }

}
