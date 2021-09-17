package ru.omega.chat.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.omega.chat.domain.ChatRoom;
import ru.omega.chat.domain.InstantMessage;
import ru.omega.chat.service.ChatRoomService;

@RestController
public class ChatRoomResource {

    @Autowired
    private ChatRoomService chatRoomService;

    @PostMapping(path = "/chatroom")
    @ResponseBody
    @ResponseStatus(code = HttpStatus.CREATED)
    public ChatRoom createChatRoom(@RequestBody ChatRoom chatRoom) {
        return chatRoomService.save(chatRoom);
    }

    @RequestMapping("/chatroom/{chatRoomId}")
    public ModelAndView join(@PathVariable String chatRoomId) {
        ModelAndView modelAndView = new ModelAndView("chatroom");
        modelAndView.addObject("chatRoom", chatRoomService.findById(chatRoomId));
        return modelAndView;
    }

    @MessageMapping("/send.message")
    public void sendMessage(@Payload InstantMessage instantMessage,
                            SimpMessageHeaderAccessor headerAccessor) {
        String chatRoomId = headerAccessor.getSessionAttributes().get("chatRoomId").toString();
        instantMessage.setChatRoomId(chatRoomId);

        if (instantMessage.isPublic()) {
            chatRoomService.sendPublicMessage(instantMessage);
        } else {
            chatRoomService.sendPrivateMessage(instantMessage);
        }
    }
}
