package com.ks.fitpass.notification.service.impl;

import com.ks.fitpass.notification.entity.ResponseMessage;
import com.ks.fitpass.notification.service.NotificationService;
import com.ks.fitpass.notification.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketServiceImpl implements WebSocketService {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final NotificationService notificationService;

    @Autowired
    public WebSocketServiceImpl(SimpMessagingTemplate simpMessagingTemplate, NotificationService notificationService){
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.notificationService = notificationService;
    }

    public void notifyFrontend(String message){
        ResponseMessage responseMessage = ResponseMessage.builder()
                .content(message)
                .build();
        notificationService.sendGlobalNotification();
        simpMessagingTemplate.convertAndSend("/all/messages",responseMessage);
    }

    @Override
    public void notifyUser(String id, String message) {
        ResponseMessage responseMessage = ResponseMessage.builder()
                .content(message)
                .build();
        notificationService.sendPrivateNotification(id);
        simpMessagingTemplate.convertAndSendToUser(id, "/all/private-messages", responseMessage);
    }

}
