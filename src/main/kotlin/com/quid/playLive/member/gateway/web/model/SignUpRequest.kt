package com.quid.playLive.member.gateway.web.model

import com.quid.playLive.member.domain.Member


data class SignUpRequest(
    val username: String,
    val password: String,
    val email: String,
    val nickname: String,
) {

    fun toMember() = Member(
        username = username,
        password = password,
        email = email,
        nickname = nickname,
    )
}