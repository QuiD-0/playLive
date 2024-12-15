package com.quid.playLive.token.gateway.api

import com.quid.playLive.global.api.Success
import com.quid.playLive.token.gateway.api.model.RefreshTokenRequest
import com.quid.playLive.token.usecase.RefreshAccessToken
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
    fun refresh(@RequestBody request: RefreshTokenRequest) =
        Success { refreshAccessToken(request.accessToken, request.refreshToken) }
}
