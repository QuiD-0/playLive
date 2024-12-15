package com.quid.playLive.token.usecase

import com.quid.playLive.token.domain.AccessToken
import com.quid.playLive.token.domain.Payload
import com.quid.playLive.token.domain.Token
import com.quid.playLive.token.gateway.repository.RefreshTokenRepository
import org.springframework.stereotype.Service

fun interface RefreshAccessToken {
    operator fun invoke(accessTokenString: String, refreshTokenString: String): AccessTokenResponse

    @Service
    class RefreshAccessTokenImpl(
        private val tokenEncoder: TokenEncoder,
        private val tokenDecoder: TokenDecoder,
        private val refreshTokenRepository: RefreshTokenRepository
    ) : RefreshAccessToken {
        override fun invoke(accessTokenString: String, refreshTokenString: String): AccessTokenResponse {
            val accessToken: Token = tokenDecoder(accessTokenString)
            val refreshToken: Token = tokenDecoder(refreshTokenString)
            require(refreshToken.isNotExpired()) { "refresh token is expired" }

            val refreshJti = refreshTokenRepository.findByUsername(accessToken.username)
            require(refreshToken.id == refreshJti) { "refresh token is not matched" }

            val newAccessToken = getNewAccessToken(accessToken.username)

            return AccessTokenResponse(newAccessToken)
        }

        private fun getNewAccessToken(username: String) =
            AccessToken(Payload.accessType(username))
                .run { tokenEncoder(this) }
    }
}

data class AccessTokenResponse(
    val accessToken: String
)
