package com.chat.controller;

import com.chat.controller.dto.ChatRequest;
import com.chat.domain.ChatHistory;
import com.chat.service.ChatService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.sql.Date;

@Controller
public class WebSocketController {

  @Autowired SimpMessagingTemplate template;
  @Autowired ChatService chatService;

  @MessageMapping("/send/message/{chatName}")
  public void sendMessage(@Payload String chatRequest, @DestinationVariable String chatName) throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    ChatRequest chat = mapper.readValue(chatRequest, ChatRequest.class);
    chatService.saveChatHistory(chat.getChatRoomId(), convertToEntity(chat));
    template.convertAndSend("/message/" + chatName, chatRequest);
  }

  private ChatHistory convertToEntity(ChatRequest chatRequest) {
    return ChatHistory.builder()
            .chatText(chatRequest.getChatText())
            .sendFrom(chatRequest.getSendFrom())
            .sendTo(chatRequest.getSendTo())
            .created(chatRequest.getCreated())
            .build();
  }
}
