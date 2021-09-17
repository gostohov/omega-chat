package ru.omega.chat.service;

import ru.omega.chat.domain.ChatRoom;
import ru.omega.chat.domain.ChatRoomUser;
import ru.omega.chat.domain.InstantMessage;

import java.util.List;

public interface ChatRoomService {
	
	ChatRoom save(ChatRoom chatRoom);
	ChatRoom findById(String chatRoomId);
	ChatRoom join(ChatRoomUser joiningUser, ChatRoom chatRoom);
	ChatRoom leave(ChatRoomUser leavingUser, ChatRoom chatRoom);
	void sendPublicMessage(InstantMessage instantMessage);
	void sendPrivateMessage(InstantMessage instantMessage);
	List<ChatRoom> findAll();
}
