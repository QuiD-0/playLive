package com.quid.playLive.chat.gateway.ws

import com.quid.playLive.chat.domain.Chat
import com.quid.playLive.chat.domain.ChatType
import com.quid.playLive.global.UUID

data class ChatRequest(
    val chatroomId: String,
    val memberId: Long,
    val message: String,
    val type: String,
    val nickname: String,
) {
    fun toDomain(): Chat {
        return Chat(
            chatroomId = UUID(chatroomId),
            senderId = memberId,
            message = message,
            chatType = ChatType.valueOf(type.uppercase()),
            nickname = nickname,
        )
    }
}
