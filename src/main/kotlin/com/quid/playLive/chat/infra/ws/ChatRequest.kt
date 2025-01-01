package com.quid.playLive.chat.infra.ws

data class ChatRequest(
    val chatroomId: String,
    val memberId: Long,
    val message: String,
    val type: String,
    val nickname: String,
)
