package com.quid.playLive.chat.gateway.event

import com.fasterxml.jackson.databind.ObjectMapper
import com.quid.playLive.chat.domain.Chat
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component

@Component
class ChatPublisher(
    private val redisTemplate: RedisTemplate<String, String>,
    private val objectMapper: ObjectMapper
) {
    fun publish(chat: Chat) {
        val data = objectMapper.writeValueAsString(chat)
        redisTemplate.convertAndSend("chat", data)
    }
}
