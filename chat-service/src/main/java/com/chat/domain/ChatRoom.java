package com.chat.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "chat_room")
public class ChatRoom {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long chatRoomId;

  @Column(name = "chat_name")
  private String chatName;

  @Column(name = "description")
  private String description;

  @Column(name = "title")
  private String title;

  @Column(name = "image_chat")
  private String imageChat;

  @OneToMany(mappedBy = "chatRoomId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<ChatHistory> chatHistories;
}
