package com.quid.playLive.token.gateway.web.response

data class TokenResponse(
    val accessToken: String,
    val refreshToken: String,
)