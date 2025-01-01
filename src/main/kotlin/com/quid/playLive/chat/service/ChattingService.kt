package com.quid.playLive.chat.service

import com.quid.playLive.chat.domain.Chat
import com.quid.playLive.chat.gateway.event.ChatPublisher
import com.quid.playLive.chat.gateway.repository.ChatRepository
import com.quid.playLive.chat.gateway.repository.ChatSessionRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession

@Service
class ChattingService(
    private val sessions: ChatSessionRepository,
    private val history: ChatRepository,
    private val redis: ChatPublisher
) {
    private val log = LoggerFactory.getLogger(this::class.java)!!

    fun enter(roomId: String, session: WebSocketSession) {
        sessions.addSession(roomId, session)
        log.info("Enter chat room: $roomId")
    }

    fun exit(roomId: String, session: WebSocketSession) {
        sessions.removeSession(roomId, session)
        log.info("Exit chat room: $roomId")
    }

    fun publishAndSave(chat: Chat, session: WebSocketSession) {
        redis.publish(chat)
        history.save(chat)
    }

    fun sendMessage(chat: Chat) {
        val message = TextMessage(chat.toJson())
        sessions.getRoom(chat.chatroomId.id)
            .parallelStream()
            .forEach { it.sendMessage(message) }
    }
}
