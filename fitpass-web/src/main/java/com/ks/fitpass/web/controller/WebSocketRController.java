package com.ks.fitpass.web.controller;

import com.ks.fitpass.notification.entity.Message;
import com.ks.fitpass.notification.service.WebSocketService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebSocketRController {
    private final WebSocketService webSocketService;

    public WebSocketRController(WebSocketService webSocketService) {
        this.webSocketService = webSocketService;
    }
    @PostMapping("/send-message")
    public void sendMessage(@RequestBody Message message){
        webSocketService.notifyFrontend(message.getMessageContent());
    }

    @PostMapping("/send-private-message/{id}")
    public void sendPrivateMessage(@PathVariable final String id,
                                   @RequestBody final Message message) {
        webSocketService.notifyUser(id, message.getMessageContent());
    }
}
