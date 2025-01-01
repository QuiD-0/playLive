package com.quid.playLive.global.redis

import com.quid.playLive.chat.infra.event.ChatSubscriber
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.data.redis.listener.RedisMessageListenerContainer

@Configuration
class RedisSubscribeConfig(
    private val redisConnectionFactory: RedisConnectionFactory,
    private val chatListener: ChatSubscriber
) {

    @Bean
    fun redisMessageListener(): RedisMessageListenerContainer {
        val container = RedisMessageListenerContainer()
        container.connectionFactory = redisConnectionFactory
        container.addMessageListener(chatListener, ChannelTopic("chat"))
        return container
    }

}
