package com.chat.controller.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatRoomDto {
  private Long chatRoomId;

  private String chatName;

  private String description;

  private String title;

  private String imageChat;

  private List<ChatHistoryDto> chatHistories;
}
