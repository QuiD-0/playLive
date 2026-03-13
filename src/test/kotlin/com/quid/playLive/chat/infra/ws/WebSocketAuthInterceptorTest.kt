package com.quid.playLive.chat.infra.ws

import com.quid.playLive.global.ws.WebSocketAuthInterceptor
import com.quid.playLive.token.domain.AccessToken
import com.quid.playLive.token.domain.Header
import com.quid.playLive.token.domain.Payload
import com.quid.playLive.token.domain.TokenType
import com.quid.playLive.token.usecase.TokenDecoder
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.web.socket.WebSocketHandler
import java.net.URI
import java.time.LocalDateTime

class WebSocketAuthInterceptorTest {

    private lateinit var tokenDecoder: TokenDecoder
    private lateinit var interceptor: WebSocketAuthInterceptor
    private lateinit var request: ServerHttpRequest
    private lateinit var response: ServerHttpResponse
    private lateinit var wsHandler: WebSocketHandler

    @BeforeEach
    fun setUp() {
        tokenDecoder = mock(TokenDecoder::class.java)
        interceptor = WebSocketAuthInterceptor(tokenDecoder)
        request = mock(ServerHttpRequest::class.java)
        response = mock(ServerHttpResponse::class.java)
        wsHandler = mock(WebSocketHandler::class.java)
    }

    @Test
    fun `유효한 토큰이면 핸드셰이크 성공`() {
        val token = AccessToken(
            Payload(
                sub = TokenType.ACCESS,
                exp = LocalDateTime.now().plusDays(1),
                username = "testuser"
            )
        )
        `when`(request.uri).thenReturn(URI("ws://localhost/chat?token=valid-token"))
        `when`(tokenDecoder.invoke("valid-token")).thenReturn(token)

        val attributes = mutableMapOf<String, Any>()
        val result = interceptor.beforeHandshake(request, response, wsHandler, attributes)

        assertTrue(result)
        assertEquals("testuser", attributes["username"])
    }

    @Test
    fun `토큰이 없으면 핸드셰이크 실패`() {
        `when`(request.uri).thenReturn(URI("ws://localhost/chat"))

        val attributes = mutableMapOf<String, Any>()
        val result = interceptor.beforeHandshake(request, response, wsHandler, attributes)

        assertFalse(result)
    }

    @Test
    fun `만료된 토큰이면 핸드셰이크 실패`() {
        val token = AccessToken(
            Header.default(),
            Payload(
                sub = TokenType.ACCESS,
                exp = LocalDateTime.now().plusSeconds(1),
                iat = LocalDateTime.now().minusDays(5),
                username = "testuser"
            )
        )
        // isExpired()가 true를 반환하도록 spy 사용
        val spiedToken = spy(token)
        `when`(spiedToken.isExpired()).thenReturn(true)
        `when`(request.uri).thenReturn(URI("ws://localhost/chat?token=expired-token"))
        `when`(tokenDecoder.invoke("expired-token")).thenReturn(spiedToken)

        val attributes = mutableMapOf<String, Any>()
        val result = interceptor.beforeHandshake(request, response, wsHandler, attributes)

        assertFalse(result)
    }

    @Test
    fun `잘못된 토큰이면 핸드셰이크 실패`() {
        `when`(request.uri).thenReturn(URI("ws://localhost/chat?token=invalid-token"))
        `when`(tokenDecoder.invoke("invalid-token")).thenThrow(RuntimeException("Invalid token"))

        val attributes = mutableMapOf<String, Any>()
        val result = interceptor.beforeHandshake(request, response, wsHandler, attributes)

        assertFalse(result)
    }
}
