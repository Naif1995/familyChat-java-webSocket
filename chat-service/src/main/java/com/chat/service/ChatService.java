package com.chat.service;

import com.chat.domain.ChatHistory;
import com.chat.domain.ChatRoom;
import com.chat.repository.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatService {

  @Autowired ChatRoomRepository chatRoomRepository;

  public String getChatRoom(String chaName) {
    return chatRoomRepository.findByChatRoom(chaName).getDescription();
  }

  public List<ChatRoom> getAllChats() {
    return (List<ChatRoom>) chatRoomRepository.findAll();
  }

  public void saveChatHistory(String chatRoomId, ChatHistory chatHistory) {
    ChatRoom chatRoom = chatRoomRepository.findById(Long.valueOf(chatRoomId)).get();
  }
}
