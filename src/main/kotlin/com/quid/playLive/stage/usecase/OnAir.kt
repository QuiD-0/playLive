package com.quid.playLive.stage.usecase

import com.quid.playLive.stage.domain.UptimeDisplay
import com.quid.playLive.stage.gateway.repository.OnAirRepository
import org.springframework.stereotype.Service

interface OnAir {

    fun start(channel: String)
    fun stop(channel: String)
    fun findBy(channel: String): UptimeDisplay
    fun exists(channel: String): Boolean
    fun list(): List<String>

    @Service
    class StageOnAir(
        private val repository: OnAirRepository
    ) : OnAir {
        override fun start(channel: String) {
            repository.save(channel, System.currentTimeMillis())
        }

        override fun stop(channel: String) {
            repository.delete(channel)
        }

        override fun findBy(channel: String): UptimeDisplay =
            repository.findByChannel(channel)

        override fun exists(channel: String): Boolean =
            repository.existsById(channel)

        override fun list(): List<String> =
            repository.findAll()
    }

}