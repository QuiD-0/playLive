package com.quid.playLive.member.gateway.web.model

data class TokenResponse(
    val accessToken: String,
    val refreshToken: String,
)