package com.quid.playLive.token.gateway.api.model

data class RefreshTokenRequest(
    val accessToken: String,
    val refreshToken: String,
) {
}
