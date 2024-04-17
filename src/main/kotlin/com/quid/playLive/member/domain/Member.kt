package com.quid.playLive.member.domain

import java.time.LocalDateTime
import java.util.UUID

data class Member(
    val id: Long? = null,
    val email: String,
    val username: String,
    val password: String,
    val nickname: String,
    val streamKey: String = UUID.randomUUID().toString().replace("-", ""),
    val regDate: LocalDateTime = LocalDateTime.now(),
) {
}