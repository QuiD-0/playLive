package com.quid.playLive.token.gateway.web

import com.quid.playLive.token.gateway.web.request.RefreshTokenRequest
import com.quid.playLive.token.usecase.RefreshAccessToken
import com.quid.playLive.member.gateway.web.model.TokenResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/token")
class RefreshTokenApiController(
    private val refreshAccessToken: RefreshAccessToken,
) {

    @PostMapping("/refresh")
    fun refresh(@RequestBody request: RefreshTokenRequest): TokenResponse =
        refreshAccessToken(request.accessToken, request.refreshToken)
}