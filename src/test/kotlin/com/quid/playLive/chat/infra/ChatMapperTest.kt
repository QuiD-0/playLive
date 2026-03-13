package com.quid.playLive.chat.infra

import com.fasterxml.jackson.databind.ObjectMapper
import com.quid.playLive.chat.domain.Chat
import com.quid.playLive.chat.domain.ChatType
import com.quid.playLive.global.UUID
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ChatMapperTest {

    private val objectMapper = ObjectMapper()

    @Test
    fun `일반 메시지는 정상적으로 JSON 변환된다`() {
        val chat = Chat(
            chatroomId = UUID("room-1"),
            senderId = 1L,
            chatType = ChatType.CHAT,
            nickname = "테스터",
            message = "안녕하세요"
        )

        val textMessage = ChatMapper.toTextMessage(chat)
        val json = objectMapper.readTree(textMessage.payload)

        assertEquals("테스터", json["nickname"].asText())
        assertEquals("안녕하세요", json["message"].asText())
    }

    @Test
    fun `큰따옴표가 포함된 메시지도 JSON이 깨지지 않는다`() {
        val chat = Chat(
            chatroomId = UUID("room-1"),
            senderId = 1L,
            chatType = ChatType.CHAT,
            nickname = "테스터",
            message = """he said "hello" to me"""
        )

        val textMessage = ChatMapper.toTextMessage(chat)
        val json = objectMapper.readTree(textMessage.payload)

        assertEquals("""he said "hello" to me""", json["message"].asText())
    }

    @Test
    fun `HTML 태그가 포함된 메시지도 유효한 JSON으로 변환된다`() {
        val chat = Chat(
            chatroomId = UUID("room-1"),
            senderId = 1L,
            chatType = ChatType.CHAT,
            nickname = "테스터",
            message = "<script>alert('xss')</script>"
        )

        val textMessage = ChatMapper.toTextMessage(chat)
        val json = objectMapper.readTree(textMessage.payload)

        // JSON 파싱이 깨지지 않고 원본 메시지가 보존됨
        assertEquals("<script>alert('xss')</script>", json["message"].asText())
    }

    @Test
    fun `백슬래시가 포함된 메시지도 JSON이 깨지지 않는다`() {
        val chat = Chat(
            chatroomId = UUID("room-1"),
            senderId = 1L,
            chatType = ChatType.CHAT,
            nickname = "테스터",
            message = """path\to\file"""
        )

        val textMessage = ChatMapper.toTextMessage(chat)
        val json = objectMapper.readTree(textMessage.payload)

        assertEquals("""path\to\file""", json["message"].asText())
    }
}
