package com.chat.controller;

import com.chat.controller.dto.ChatHistoryDto;
import com.chat.controller.dto.ChatRoomDto;
import com.chat.controller.dto.ChatRoomListDto;
import com.chat.domain.ChatRoom;
import com.chat.service.ChatService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(originPatterns = "*")
@RequestMapping("/chats")
public class ChatApi {

  @Autowired ChatService chatService;

  @Autowired private ModelMapper modelMapper;

  @GetMapping
  public ChatRoomListDto getAllChats() {
    return convertToDTO(chatService.getAllChats());
  }

  @GetMapping("/name/{chatName}")
  public String getChatByChatName(@PathVariable("chatName") String chatName) {
    return chatService.getChatRoom(chatName);
  }

  private ChatRoomListDto convertToDTO(List<ChatRoom> chatRoomList) {
    modelMapper.getConfiguration().setAmbiguityIgnored(true);
    return ChatRoomListDto.builder()
        .chatRoomDtoList(
            chatRoomList.stream()
                .map(e -> modelMapper.map(e, ChatRoomDto.class))
                .collect(Collectors.toList()))
        .build();
  }
}
