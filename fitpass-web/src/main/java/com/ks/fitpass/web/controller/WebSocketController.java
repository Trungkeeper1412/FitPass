package com.ks.fitpass.web.controller;

import com.ks.fitpass.core.entity.ResponseMessage;
import com.ks.fitpass.core.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;


@Controller
public class WebSocketController {
    @MessageMapping("/application")
    @SendTo("/all/messages")
    public ResponseMessage getMessage(final Message message) throws Exception {
        Thread.sleep(1000);
        return new ResponseMessage(HtmlUtils.htmlEscape(message.getMessageContent()));
    }
}
