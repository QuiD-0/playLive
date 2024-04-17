package com.quid.playLive.stage.gateway.repository.cache

import org.springframework.stereotype.Component

@Component
class StreamKeyRedisRepository(
    private val redis
) {
    fun findByChannel(channel: String): String? = //todo

    fun merge(channel: String, streamKey: String) {
        //todo
    }
}