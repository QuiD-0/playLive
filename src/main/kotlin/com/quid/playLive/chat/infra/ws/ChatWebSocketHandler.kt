package com.quid.playLive.chat.infra.ws

import com.fasterxml.jackson.databind.ObjectMapper
import com.quid.playLive.chat.domain.ChatType.CHAT
import com.quid.playLive.chat.domain.ChatType.JOIN
import com.quid.playLive.chat.domain.ChatType.LEAVE
import com.quid.playLive.chat.domain.ChatType.PING
import com.quid.playLive.chat.infra.ChatMapper
import com.quid.playLive.chat.service.ChattingService
import org.springframework.stereotype.Component
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

@Component
class ChatWebSocketHandler(
    private val objectMapper: ObjectMapper,
    private val chatting: ChattingService
) : TextWebSocketHandler() {

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        val chat = objectMapper.readValue(message.payload, ChatRequest::class.java)
            .let { ChatMapper.toDomain(it) }
        when (chat.chatType) {
            CHAT -> chatting.publishAndSave(chat, session)
            JOIN -> chatting.enter(chat.chatroomId.id, session)
            LEAVE -> chatting.exit(chat.chatroomId.id, session)
            PING -> Unit
        }
    }
}
