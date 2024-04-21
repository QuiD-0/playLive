package com.quid.playLive.token.gateway.repository

import com.quid.playLive.token.gateway.repository.redis.RedisRefreshTokenRepository
import com.quid.playLive.token.gateway.repository.redis.UserTokenJti
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

interface RefreshTokenRepository {

    fun save(userToken: UserTokenJti)
    fun findByUsername(username: String): String
    fun deleteByUsername(username: String)

    @Repository
    class RefreshTokenRepositoryImpl(
        private val repository: RedisRefreshTokenRepository
    ) : RefreshTokenRepository {

        override fun save(userToken: UserTokenJti) {
            repository.save(userToken)
        }

        override fun findByUsername(username: String): String=
            repository.findByIdOrNull(username)
                ?.refreshTokenJti
                ?: throw IllegalArgumentException("RefreshToken not found: $username")

        override fun deleteByUsername(username: String) {
            repository.deleteById(username)
        }
    }
}