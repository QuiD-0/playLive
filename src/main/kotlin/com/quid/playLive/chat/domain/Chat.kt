package com.quid.playLive.chat.domain

import com.quid.playLive.global.UUID

data class Chat(
    val id: UUID = UUID(),
    val chatroomId: String,
    val memberId: Long,
    val message: String,
    val regDate: String
) {
}

