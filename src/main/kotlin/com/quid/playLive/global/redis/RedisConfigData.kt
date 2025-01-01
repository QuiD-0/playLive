package com.quid.playLive.global.redis

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "spring.data.redis")
data class RedisConfigData(
    val host: String,
    val port: Int
)
