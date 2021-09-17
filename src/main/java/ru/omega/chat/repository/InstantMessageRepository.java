package ru.omega.chat.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import ru.omega.chat.domain.InstantMessage;

import java.util.List;

public interface InstantMessageRepository extends CassandraRepository<InstantMessage, String> {
	
	List<InstantMessage> findInstantMessagesByUsernameAndChatRoomId(String username, String chatRoomId);
}
