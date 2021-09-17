package ru.omega.chat.service;

import ru.omega.chat.domain.InstantMessage;

import java.util.List;

public interface InstantMessageService {

    void appendInstantMessageToConversations(InstantMessage instantMessage);

    List<InstantMessage> findAllInstantMessagesFor(String username, String chatRoomId);
}
