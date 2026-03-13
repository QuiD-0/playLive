package com.quid.playLive.chat.infra

import com.fasterxml.jackson.databind.ObjectMapper
import com.quid.playLive.chat.domain.Chat
import com.quid.playLive.chat.domain.ChatType
import com.quid.playLive.chat.infra.repository.ChatEntity
import com.quid.playLive.chat.infra.ws.ChatRequest
import com.quid.playLive.global.UUID
import org.springframework.web.socket.TextMessage

data object ChatMapper {
    private val objectMapper = ObjectMapper()

    fun toTextMessage(chat: Chat): TextMessage {
        val map = mapOf(
            "id" to chat.id.id,
            "nickname" to chat.nickname,
            "message" to chat.message,
            "regDate" to chat.regDate.toString()
        )
        return TextMessage(objectMapper.writeValueAsString(map))
    }

    fun toDomain(chat: ChatRequest): Chat {
        return Chat(
            chatroomId = UUID(chat.chatroomId),
            senderId = chat.memberId,
            message = chat.message,
            chatType = ChatType.valueOf(chat.type.uppercase()),
            nickname = chat.nickname,
        )
    }

    fun toDomain(chat: ChatRequest, memberId: Long, nickname: String): Chat {
        return Chat(
            chatroomId = UUID(chat.chatroomId),
            senderId = memberId,
            message = chat.message,
            chatType = ChatType.valueOf(chat.type.uppercase()),
            nickname = nickname,
        )
    }

    fun toDomain(chat: ChatEntity): Chat {
        return Chat(
            id = UUID(chat.id),
            chatroomId = UUID(chat.chatroomId),
            senderId = chat.senderId,
            chatType = ChatType.valueOf(chat.chatType),
            nickname = chat.nickname,
            message = chat.message,
            regDate = chat.regDate
        )
    }

    fun toEntity(chat: Chat): ChatEntity {
        return ChatEntity(
            id = chat.id.id,
            chatroomId = chat.chatroomId.id,
            senderId = chat.senderId,
            chatType = chat.chatType.name,
            nickname = chat.nickname,
            message = chat.message,
            regDate = chat.regDate
        )
    }
}
