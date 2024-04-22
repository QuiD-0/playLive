package com.quid.playLive.token.gateway.api.request

data class RefreshTokenRequest(
    val accessToken: String,
    val refreshToken: String,
) {
}