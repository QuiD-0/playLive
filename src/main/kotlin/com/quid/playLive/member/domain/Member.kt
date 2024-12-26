package com.quid.playLive.member.domain

import java.time.LocalDateTime

data class Member(
    val id: Long? = null,
    val email: String,
    val username: String,
    val password: String,
    val nickname: String,
    val avatar: String = "",
    val regDate: LocalDateTime = LocalDateTime.now(),
) {
    init {
        require(email.isNotBlank()) { "Email is empty" }
        require(username.isNotBlank()) { "Username is empty" }
        require(password.isNotBlank()) { "Password is empty" }
        require(nickname.isNotBlank()) { "Nickname is empty" }
    }

    fun encodePassword(encodedPassword : String): Member {
        return this.copy(password = encodedPassword)
    }

    fun updateProfile(nickname: String): Member {
        return this.copy(nickname = nickname)
    }
}
