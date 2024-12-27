package com.quid.playLive.chat.service

import com.quid.playLive.chat.domain.Chat
import com.quid.playLive.chat.domain.ChatType
import com.quid.playLive.chat.gateway.event.ChatPublisher
import com.quid.playLive.chat.gateway.repository.ChatRepository
import com.quid.playLive.chat.gateway.repository.ChatSessionRepository
import org.springframework.stereotype.Service
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession

@Service
class ChattingService(
    private val sessions: ChatSessionRepository,
    private val history: ChatRepository,
    private val redis: ChatPublisher
) {

    fun publish(chat: Chat, session: WebSocketSession) {
        when (chat.chatType) {
            ChatType.CHAT -> history.save(chat)
            ChatType.JOIN -> sessions.addRoom(chat.chatroomId, session)
            ChatType.LEAVE -> sessions.removeRoom(chat.chatroomId, session)
        }
        redis.publish(chat)
    }

    fun sendMessage(chat: Chat) {
        val message = TextMessage(chat.toJson())
        sessions.getRoom(chat.chatroomId)
            .parallelStream()
            .forEach { it.sendMessage(message) }
    }
}
