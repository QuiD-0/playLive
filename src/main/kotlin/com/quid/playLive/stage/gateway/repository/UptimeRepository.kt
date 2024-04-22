package com.quid.playLive.stage.gateway.repository

import com.quid.playLive.stage.gateway.repository.cache.UptimeRedisHash
import com.quid.playLive.stage.gateway.repository.cache.UptimeRedisRepository
import com.quid.playLive.stage.domain.UptimeDisplay
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

interface UptimeRepository {

    fun findByChannel(channel: String): UptimeDisplay

    fun save(channel: String, uptime: Long)

    fun delete(channel: String)

    fun existsById(channel: String): Boolean

    @Repository
    class UptimeRepositoryImpl(
        private val repository: UptimeRedisRepository
    ) : UptimeRepository {
        override fun findByChannel(channel: String): UptimeDisplay =
            repository.findByIdOrNull(channel)
                ?.let { UptimeDisplay(it.channel, it.uptime) }
                ?: UptimeDisplay(channel, 0, 0)

        override fun save(channel: String, uptime: Long) {
            repository.save(UptimeRedisHash(channel, uptime))
        }

        override fun delete(channel: String) {
            repository.deleteById(channel)
        }

        override fun existsById(channel: String): Boolean =
            repository.existsById(channel)
    }

}