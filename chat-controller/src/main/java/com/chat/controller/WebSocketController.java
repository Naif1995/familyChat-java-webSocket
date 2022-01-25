package com.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import services.ChatService;

@Controller
public class WebSocketController {


    @Autowired
    SimpMessagingTemplate template;
    @Autowired
    ChatService chatService;

    @MessageMapping("/send/message/{chatName}")
    public void sendMessage(@Payload String body, @DestinationVariable String chatName) {
        System.out.println(body);
        template.convertAndSend("/message/"+chatName, "Welcome to "+chatName);
    }
}
