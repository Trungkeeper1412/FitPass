package com.ks.fitpass.core.service.impl;

import com.ks.fitpass.core.entity.ResponseMessage;
import com.ks.fitpass.core.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketServiceImpl implements WebSocketService {
    private final SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public WebSocketServiceImpl(SimpMessagingTemplate simpMessagingTemplate){
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    public void notifyFrontend(String message){
        ResponseMessage responseMessage = ResponseMessage.builder()
                .content(message)
                .build();
        simpMessagingTemplate.convertAndSend("/all/messages",responseMessage);
    }

}
