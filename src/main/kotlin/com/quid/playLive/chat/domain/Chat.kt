package com.quid.playLive.chat.domain

import com.quid.playLive.global.UUID
import java.time.LocalDateTime

data class Chat(
    val id: UUID = UUID(),
    val chatroomId: UUID,
    val senderId: Long,
    val chatType: ChatType,
    val nickname: String,
    val message: String,
    val regDate: LocalDateTime = LocalDateTime.now(),
) {
    fun toJson(): String {
        return """
            {
                "id": "$id",
                "chatroomId": "$chatroomId",
                "nickname": "$nickname",
                "message": "$message",
                "regDate": "$regDate"
            }
        """.trimIndent()
    }
}

