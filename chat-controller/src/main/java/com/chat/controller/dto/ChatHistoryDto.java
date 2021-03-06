package com.chat.controller.dto;

import lombok.*;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatHistoryDto {

  private Long chatHistoryId;

  private String sendFrom;

  private String sendTo;

  private String chatText;

  private Timestamp created;
}
