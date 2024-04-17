package com.quid.playLive.stage.gateway.repository.cache

import org.springframework.stereotype.Component

@Component
class InMemoryStreamKey {
    private val memory = mutableMapOf<String, String>()

    fun findByChannel(channel: String): String? = memory.getOrElse(channel) { null }

    fun merge(channel: String, streamKey: String) {
        memory[channel] = streamKey
    }
}