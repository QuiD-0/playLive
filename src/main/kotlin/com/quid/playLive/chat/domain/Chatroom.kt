package com.quid.playLive.chat.domain

import com.quid.playLive.global.UUID
import java.time.LocalDateTime

data class Chatroom(
    val id: UUID = UUID(),
    val memberId: Long,
    val regDate: LocalDateTime = LocalDateTime.now(),
) {
}
