package com.next.chat;

import java.io.IOException;
import java.nio.channels.SeekableByteChannel;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ChatRoom {
	
	private String roomId;
	private String name;
	private Set<WebSocketSession> sessions = new HashSet<>();

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	
	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<WebSocketSession> getSessions() {
		return sessions;
	}

	public void setSessions(Set<WebSocketSession> sessions) {
		this.sessions = sessions;
	}

	public static ChatRoom create(String name,String roomId) {
		ChatRoom chatRoom = new ChatRoom();
		//chatRoom.roomId = UUID.randomUUID().toString();
		chatRoom.roomId=roomId;
		chatRoom.name = name;
		return chatRoom;
	}

	//roomView에서 webSocket.send 가 실행될 떄 실행되는 메소드
	public void handleMessage(WebSocketSession session, ChatMessageVO chatMessageVO, ObjectMapper objectMapper)
			throws IOException {
		if (chatMessageVO.getType() == MessageType.ENTER) {
			sessions.add(session);
			chatMessageVO.setMessage(chatMessageVO.getWriter() + "님이 입장하셨습니다.");
		} else if (chatMessageVO.getType() == MessageType.LEAVE) {
			System.out.println("session사이즈" + sessions.size());
			System.out.println("삭제할세션" + session);

			chatMessageVO.setMessage(chatMessageVO.getWriter() + "님임 퇴장하셨습니다.");
			sessions.remove(session);
		} else {
			chatMessageVO.setMessage(chatMessageVO.getWriter() + " : " + chatMessageVO.getMessage());
		}
		send(chatMessageVO, objectMapper);
	}

	private void send(ChatMessageVO chatMessageVO, ObjectMapper objectMapper) throws IOException {
		TextMessage textMessage = new TextMessage(objectMapper.writeValueAsString(chatMessageVO.getMessage()));
		for (WebSocketSession sess : sessions) {
			sess.sendMessage(textMessage);
		}
	}

}
