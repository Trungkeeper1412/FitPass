package com.ks.fitpass.web.controller;


import com.ks.fitpass.notification.entity.Notification;
import com.ks.fitpass.notification.service.WebSocketService;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebSocketRController {
    private final WebSocketService webSocketService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    public WebSocketRController(WebSocketService webSocketService, SimpMessagingTemplate simpMessagingTemplate) {
        this.webSocketService = webSocketService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }
    @PostMapping("/send-message")
    public void sendMessage(@RequestBody Notification notification){

//        webSocketService.notifyFrontend(notification);
        simpMessagingTemplate.convertAndSend("/all/messages",notification);
    }

    @PostMapping("/send-private-message/{id}")
    public void sendPrivateMessage(@PathVariable int id, @RequestBody Notification notification) {
        webSocketService.notifyUser(id,notification);
    }

    @PostMapping ("/send-private-response/{id}")
        public void sendPrivateResponse(@PathVariable int id, @RequestBody Notification notification){
        webSocketService.notifyEmployee(id,notification);
    }
}
