package com.chat.controller;

import com.chat.controller.dto.ChatRequest;
import com.chat.domain.ChatHistory;
import com.chat.service.ChatService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;


@Controller
public class WebSocketController {

  @Autowired SimpMessagingTemplate template;
  @Autowired ChatService chatService;

  @MessageMapping("/send/message/rooms")
  public void sendMessage(@Payload String chatRequest) throws JsonProcessingException {
    System.out.println(chatRequest);
    ObjectMapper mapper = new ObjectMapper();
    ChatRequest chat = mapper.readValue(chatRequest, ChatRequest.class);
    Long chatHistoryId = chatService.saveChatHistory(chat.getChatRoomId(), convertToEntity(chat));
    chat.setChatHistoryId(chatHistoryId.toString());
    template.convertAndSend("/message/rooms", chat);
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
