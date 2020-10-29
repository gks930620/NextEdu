package com.next.chat;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MultiChatHandler extends TextWebSocketHandler {
	
	@Inject
	private  ChatRoomRepository chatRoomRepository;
	@Inject
	private  ObjectMapper objectMapper;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
    @Override    //여기로 roomVIew에서 이 메시지로 어떻게 가는거지?
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        logger.info("메세지 전송 = {} : {}",session,message.getPayload());
        String msg = message.getPayload();
        ChatMessageVO chatMessage = objectMapper.readValue(msg,ChatMessageVO.class);
        ChatRoom chatRoom = chatRoomRepository.findRoomById(chatMessage.getChatRoomId());
        logger.info("chatroom = {}",chatRoom);
        chatRoom.handleMessage(session,chatMessage,objectMapper);
    }
}
