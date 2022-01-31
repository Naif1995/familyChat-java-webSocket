package com.chat.repository;

import com.chat.domain.ChatRoom;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRoomRepository extends CrudRepository<ChatRoom, Long> {

  @Query(
      nativeQuery = true,
      value =
          "SELECT  chat_room_id ,chat_name,description,title,image_chat FROM chat_room  where chat_name = :chatName")
  ChatRoom findByChatRoom(@Param("chatName") String chatName);
}
