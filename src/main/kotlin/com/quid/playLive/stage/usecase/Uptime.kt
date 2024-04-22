package com.quid.playLive.stage.usecase

import com.quid.playLive.stage.domain.UptimeDisplay
import com.quid.playLive.stage.gateway.repository.UptimeRepository
import org.springframework.stereotype.Service

interface Uptime {

    fun start(channel: String)

    fun stop(channel: String)

    fun findBy(channel: String): UptimeDisplay

    fun exists(channel: String): Boolean

    @Service
    class StageUptime(
        private val repository: UptimeRepository
    ) : Uptime {
        override fun start(channel: String) {
            repository.save(channel, System.currentTimeMillis())
        }

        override fun stop(channel: String) {
            repository.delete(channel)
        }

        override fun findBy(channel: String): UptimeDisplay =
            repository.findByChannel(channel)

        override fun exists(channel: String): Boolean {
            return repository.existsById(channel)
        }
    }

}