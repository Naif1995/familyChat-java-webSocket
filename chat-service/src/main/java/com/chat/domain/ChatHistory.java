package com.chat.domain;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder@Table(name = "chat_history")
public class ChatHistory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long chatHistoryId;

  @ManyToOne
  @JoinColumn(name = "chat_room_id")
  private ChatRoom chatRoom;

  @Column(name = "send_from")
  private String sendFrom;

  @Column(name = "send_to")
  private String sendTo;

  @Column(name = "chat_text")
  private String chatText;

  @Column(name = "created")
  private Timestamp created;

  public void addChatRoom(ChatRoom chatRoom) {
    setChatRoom(chatRoom);
  }
}
