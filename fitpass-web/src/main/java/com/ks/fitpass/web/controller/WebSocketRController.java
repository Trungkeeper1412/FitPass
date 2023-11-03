package com.ks.fitpass.web.controller;

import com.ks.fitpass.core.entity.Message;
import com.ks.fitpass.core.service.WebSocketService;
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
}
