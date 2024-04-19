package com.quid.playLive.stage.gateway.repository.cache

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash
data class StreamKeyRedisHash(
    @Id
    val channel: String,
    val streamKey: String
)
