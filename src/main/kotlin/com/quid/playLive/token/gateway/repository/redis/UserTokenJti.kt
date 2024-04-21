package com.quid.playLive.token.gateway.repository.redis

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash(timeToLive = 86400)
data class UserTokenJti(
    @Id
    val username: String,
    val refreshTokenJti: String,
) {
}