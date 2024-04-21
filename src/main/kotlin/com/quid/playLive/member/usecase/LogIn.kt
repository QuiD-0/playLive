package com.quid.playLive.member.usecase

import com.quid.playLive.member.domain.MemberDetail
import com.quid.playLive.token.domain.AccessToken
import com.quid.playLive.token.domain.Payload
import com.quid.playLive.token.domain.RefreshToken
import com.quid.playLive.token.gateway.repository.RefreshTokenRepository
import com.quid.playLive.token.gateway.repository.redis.UserTokenJti
import com.quid.playLive.member.gateway.web.model.TokenResponse
import com.quid.playLive.token.usecase.TokenEncoder
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.stereotype.Service

fun interface LogIn {
    operator fun invoke(username: String, password: String): TokenResponse

    @Service
    class LoginUseCase(
        private val authenticationManagerBuilder: AuthenticationManagerBuilder,
        private val tokenEncoder: TokenEncoder,
        private val refreshTokenRepository: RefreshTokenRepository,
    ) : LogIn {

        override fun invoke(username: String, password: String): TokenResponse {
            val accessToken = UsernamePasswordAuthenticationToken(username, password)
                .let { authenticationManagerBuilder.getObject().authenticate(it) }
                .let { it.principal as MemberDetail }
                .let { Payload.accessType(it.username) }
                .let { AccessToken(it) }
                .let { tokenEncoder(it) }

            val refreshToken = RefreshToken()
                .also { refreshTokenRepository.save(UserTokenJti(username, it.id)) }
                .run { tokenEncoder(this) }

            return TokenResponse(accessToken, refreshToken)
        }

    }
}