package com.quid.playLive.chat.infra.repository

import org.springframework.stereotype.Component
import org.springframework.web.socket.WebSocketSession

@Component
class ChatSessionRepository {
    private val chatRooms = mutableMapOf<String, MutableSet<WebSocketSession>>()

    fun addSession(chatroomId: String, sessions: WebSocketSession) {
        chatRooms.computeIfAbsent(chatroomId) { mutableSetOf() }.add(sessions)
    }

    fun removeSession(chatroomId: String, sessions: WebSocketSession) {
        chatRooms[chatroomId]?.remove(sessions)
    }

    fun getRoom(chatroomId: String): MutableSet<WebSocketSession> {
        return chatRooms[chatroomId] ?: mutableSetOf()
    }
}
