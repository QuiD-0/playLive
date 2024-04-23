package com.quid.playLive.stage.gateway.repository

import com.quid.playLive.stage.domain.UptimeDisplay
import com.quid.playLive.stage.gateway.repository.cache.OnAirRedisHash
import com.quid.playLive.stage.gateway.repository.cache.OnAirRedisRepository
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

interface OnAirRepository {

    fun findByChannel(channel: String): UptimeDisplay
    fun save(channel: String, uptime: Long)
    fun delete(channel: String)
    fun existsById(channel: String): Boolean
    fun findAll(): List<String>
    fun findAll(pageable: Pageable): List<String>

    @Repository
    class OnAirRepositoryImpl(
        private val repository: OnAirRedisRepository
    ) : OnAirRepository {
        override fun findByChannel(channel: String): UptimeDisplay =
            repository.findByIdOrNull(channel)
                ?.let { UptimeDisplay(it.channel, it.uptime) }
                ?: UptimeDisplay(channel, 0, 0)

        override fun save(channel: String, uptime: Long) {
            repository.save(OnAirRedisHash(channel, uptime))
        }

        override fun delete(channel: String) {
            repository.deleteById(channel)
        }

        override fun existsById(channel: String): Boolean =
            repository.existsById(channel)

        override fun findAll(): List<String> =
            repository.findAll().map { it.channel }

        override fun findAll(pageable: Pageable): List<String> =
            repository.findAll(pageable).map { it.channel }.toList()
    }

}