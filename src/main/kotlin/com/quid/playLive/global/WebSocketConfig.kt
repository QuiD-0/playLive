package com.quid.playLive.global

import com.quid.playLive.chat.infra.ws.ChatWebSocketHandler
import com.quid.playLive.global.ws.WebSocketAuthInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry

@Configuration
@EnableWebSocket
class WebSocketConfig(
    private val chatWebSocketHandler: ChatWebSocketHandler,
    private val webSocketAuthInterceptor: WebSocketAuthInterceptor
) : WebSocketConfigurer {
    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        registry.addHandler(chatWebSocketHandler, "/chat")
            .addInterceptors(webSocketAuthInterceptor)
            .setAllowedOrigins("*")
    }
}

