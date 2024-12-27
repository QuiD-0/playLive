package com.quid.playLive.chat.gateway.ws

import com.fasterxml.jackson.databind.ObjectMapper
import com.quid.playLive.chat.domain.Chat
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

@Component
class ChatWebSocketHandler(
    private val objectMapper: ObjectMapper
) : TextWebSocketHandler() {
    private val log = LoggerFactory.getLogger(this::class.java)!!

    private val sessions = mutableSetOf<WebSocketSession>()

    override fun afterConnectionEstablished(session: WebSocketSession) {
        sessions.add(session)
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        sessions.remove(session)
    }

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        log.info(message.toString())
        log.info("Received message: ${message.payload}")
        sessions.parallelStream().forEach {
            it.sendMessage(message)
        }
    }

    fun sendMessage(chat: Chat) {
        log.info("Sending message: $chat")
        val message = objectMapper.writeValueAsString(chat)
        sessions.parallelStream().forEach {
            it.sendMessage(TextMessage(message))
        }
    }

}
