package com.quid.playLive.chat.domain

import com.quid.playLive.global.UUID
import java.time.LocalDateTime

data class Chat(
    val id: UUID = UUID(),
    val chatroomId: String,
    val memberId: Long,
    val message: String,
    val regDate: LocalDateTime = LocalDateTime.now(),
) {
}

