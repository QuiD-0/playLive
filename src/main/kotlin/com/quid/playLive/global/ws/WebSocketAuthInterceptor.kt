package com.quid.playLive.global.ws

import com.quid.playLive.token.usecase.TokenDecoder
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.stereotype.Component
import org.springframework.web.socket.WebSocketHandler
import org.springframework.web.socket.server.HandshakeInterceptor

@Component
class WebSocketAuthInterceptor(
    private val tokenDecoder: TokenDecoder
) : HandshakeInterceptor {

    override fun beforeHandshake(
        request: ServerHttpRequest,
        response: ServerHttpResponse,
        wsHandler: WebSocketHandler,
        attributes: MutableMap<String, Any>
    ): Boolean {
        val query = request.uri.query ?: return false
        val token = query.split("&")
            .map { it.split("=") }
            .find { it[0] == "token" }
            ?.getOrNull(1) ?: return false

        return try {
            val decoded = tokenDecoder(token)
            if (decoded.isExpired()) return false
            attributes["username"] = decoded.username
            true
        } catch (e: Exception) {
            false
        }
    }

    override fun afterHandshake(
        request: ServerHttpRequest,
        response: ServerHttpResponse,
        wsHandler: WebSocketHandler,
        exception: Exception?
    ) {}
}
