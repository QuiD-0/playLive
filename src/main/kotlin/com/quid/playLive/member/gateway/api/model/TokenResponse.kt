package com.quid.playLive.member.gateway.api.model

data class TokenResponse(
    val accessToken: String,
    val refreshToken: String,
)