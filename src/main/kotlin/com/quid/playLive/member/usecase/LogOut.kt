package com.quid.playLive.member.usecase

import com.quid.playLive.token.gateway.repository.RefreshTokenRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

interface LogOut {
    operator fun invoke(username: String)

    @Service
    class LogOutUseCase(
        private val refreshTokenRepository: RefreshTokenRepository
    ): LogOut {
        override fun invoke(username: String) {
            SecurityContextHolder.clearContext()
            refreshTokenRepository.deleteByUsername(username)
        }
    }
}
