package ru.omega.chat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.omega.chat.domain.ChatRoom;
import ru.omega.chat.domain.InstantMessage;
import ru.omega.chat.repository.InstantMessageRepository;
import ru.omega.chat.service.ChatRoomService;
import ru.omega.chat.service.InstantMessageService;

import java.util.List;

@Service
public class CassandraInstantMessageService implements InstantMessageService {

	@Autowired
	private InstantMessageRepository instantMessageRepository;
	
	@Autowired
	private ChatRoomService chatRoomService;
	
	@Override
	public void appendInstantMessageToConversations(InstantMessage instantMessage) {
		if (instantMessage.isPublic()) {
			ChatRoom chatRoom = chatRoomService.findById(instantMessage.getChatRoomId());
			
			chatRoom.getConnectedUsers().forEach(connectedUser -> {
				instantMessage.setUsername(connectedUser.getUsername());
				instantMessageRepository.save(instantMessage);
			});
		} else {
			instantMessage.setUsername(instantMessage.getFromUser());
			instantMessageRepository.save(instantMessage);
			
			instantMessage.setUsername(instantMessage.getToUser());
			instantMessageRepository.save(instantMessage);
		}
	}

	@Override
	public List<InstantMessage> findAllInstantMessagesFor(String username, String chatRoomId) {
		return instantMessageRepository.findInstantMessagesByUsernameAndChatRoomId(username, chatRoomId);
	}
}
