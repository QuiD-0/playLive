package com.quid.playLive.chat.gateway.event

import com.fasterxml.jackson.databind.ObjectMapper
import com.quid.playLive.chat.domain.Chat
import com.quid.playLive.chat.service.ChattingService
import org.springframework.data.redis.connection.Message
import org.springframework.data.redis.connection.MessageListener
import org.springframework.stereotype.Component

@Component
class ChatSubscriber(
    private val objectMapper: ObjectMapper,
    private val chat: ChattingService
) : MessageListener {
    override fun onMessage(message: Message, pattern: ByteArray?) {
        val data = objectMapper.readValue(message.body, Chat::class.java)
        chat.sendMessage(data)
    }
}
