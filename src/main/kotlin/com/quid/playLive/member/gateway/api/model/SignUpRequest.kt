package com.quid.playLive.member.gateway.api.model

import com.quid.playLive.member.domain.Member

data class SignUpRequest(
    val username: String,
    val password: String,
    val email: String,
    val nickname: String,
) {
    init {
        require(email.matches(Regex("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}"))) {
            "Invalid email format"
        }
    }

    fun toMember() = Member(
        username = username,
        password = password,
        email = email,
        nickname = nickname,
    )
}