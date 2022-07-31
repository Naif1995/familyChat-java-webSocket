package com.chat.service;

import com.chat.domain.ChatHistory;
import com.chat.domain.ChatRoom;
import com.chat.repository.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class ChatService {

  @Autowired ChatRoomRepository chatRoomRepository;

  public String getChatRoom(String chaName) {
    return chatRoomRepository.findByChatRoom(chaName).getDescription();
  }

  Comparator<ChatHistory> comparator = (c1, c2) -> {
    return Long.valueOf(c1.getCreated().getTime()).compareTo(c2.getCreated().getTime());
  };

  public List<ChatRoom> getAllChats() {
    List<ChatRoom> chatRoomList = (List<ChatRoom>) chatRoomRepository.findAll();
    chatRoomList.forEach(
            chatRoom -> {
//              System.out.println(chatRoom.getChatHistories());
              List<ChatHistory> chatHistory = chatRoom.getChatHistories();
              Collections.sort(chatHistory, comparator);
              chatRoom.setChatHistories(chatHistory);
            }
    );
    return chatRoomList;
  }

  public Long saveChatHistory(String chatRoomId, ChatHistory chatHistory) {
    ChatRoom chatRoom = chatRoomRepository.findById(Long.valueOf(chatRoomId)).get();
    chatHistory.addChatRoom(chatRoom);
    chatRoom.getChatHistories().add(chatHistory);
    ChatRoom chat = chatRoomRepository.save(chatRoom);
    return chat.getChatHistories().get(chat.getChatHistories().size() - 1).getChatHistoryId();
  }
}
