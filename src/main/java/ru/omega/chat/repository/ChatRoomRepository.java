package ru.omega.chat.repository;

import org.springframework.data.repository.CrudRepository;
import ru.omega.chat.domain.ChatRoom;

public interface ChatRoomRepository extends CrudRepository<ChatRoom, String> {

}
