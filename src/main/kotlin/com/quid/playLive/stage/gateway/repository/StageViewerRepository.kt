package com.quid.playLive.stage.gateway.repository

import com.quid.playLive.stage.domain.StageViewer
import com.quid.playLive.stage.gateway.repository.cache.StageViewerRedisHash
import com.quid.playLive.stage.gateway.repository.cache.StageViewerRedisRepository
import org.springframework.stereotype.Repository

interface StageViewerRepository {
    fun findByChannel(channel: String): StageViewer
    fun save(it: StageViewer)

    @Repository
    class StageViewerRepositoryImpl(
        private val cache: StageViewerRedisRepository
    ) : StageViewerRepository {

        override fun findByChannel(channel: String): StageViewer =
            cache.findByChannel(channel)
                ?.toDomain()
                ?: StageViewer(channel, mutableSetOf())

        override fun save(it: StageViewer) {
            cache.save(StageViewerRedisHash(it))
        }
    }
}