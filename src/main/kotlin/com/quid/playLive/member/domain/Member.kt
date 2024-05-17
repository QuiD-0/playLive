package com.quid.playLive.member.domain

import java.time.LocalDateTime
import java.util.UUID

data class Member(
    val id: Long? = null,
    val email: String,
    val username: String,
    val password: String,
    val nickname: String,
    val avatar: String = "",
    val streamKey: String = UUID.randomUUID().toString().replace("-", ""),
    val regDate: LocalDateTime = LocalDateTime.now(),
) {
    fun encodePassword(encodedPassword : String): Member {
        return this.copy(password = encodedPassword)
    }
}
