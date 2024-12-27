package com.quid.playLive.chat.gateway.repository

import org.springframework.stereotype.Component
import org.springframework.web.socket.WebSocketSession

@Component
class ChatSessionRepository {
    private val chatRooms = mutableMapOf<String, MutableSet<WebSocketSession>>()

    fun addRoom(chatroomId: String, sessions: WebSocketSession) {
        chatRooms.computeIfAbsent(chatroomId) { mutableSetOf() }.add(sessions)
    }

    fun removeRoom(chatroomId: String, sessions: WebSocketSession) {
        chatRooms[chatroomId]?.remove(sessions)
    }

    fun getRoom(chatroomId: String): MutableSet<WebSocketSession> {
        return chatRooms[chatroomId] ?: mutableSetOf()
    }
}
