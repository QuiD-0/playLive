package com.quid.playLive.stage.gateway.repository

import com.quid.playLive.stage.domain.StageViewer
import com.quid.playLive.stage.gateway.repository.cache.StageViewerRedisHash
import com.quid.playLive.stage.gateway.repository.cache.StageViewerRedisRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

interface StageViewerRepository {
    fun findByChannel(channel: String): StageViewer
    fun save(it: StageViewer)

    @Repository
    class StageViewerRepositoryImpl(
        private val cache: StageViewerRedisRepository
    ) : StageViewerRepository {

        @Transactional(readOnly = true)
        override fun findByChannel(channel: String): StageViewer =
            cache.findByChannel(channel)
                ?.toDomain()
                ?: StageViewer(channel, mutableSetOf())

        @Transactional
        override fun save(it: StageViewer) {
            cache.save(StageViewerRedisHash(it))
        }
    }
}