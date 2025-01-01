package com.quid.playLive.chat.gateway.event

import com.quid.playLive.chat.domain.Chat
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component

@Component
class ChatPublisher(
    private val redisTemplate: RedisTemplate<String, String>,
) {
    fun publish(chat: Chat) {
        redisTemplate.convertAndSend("chat", chat.toJson())
    }
}
