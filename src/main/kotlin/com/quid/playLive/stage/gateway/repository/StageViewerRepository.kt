package com.quid.playLive.stage.gateway.repository

import com.quid.playLive.stage.domain.StageViewer
import com.quid.playLive.stage.gateway.repository.cache.StageViewerRedisRepository
import org.springframework.stereotype.Repository

interface StageViewerRepository {
    fun findByChannel(channel: String): StageViewer
    fun merge(it: StageViewer)

    @Repository
    class StageViewerRepositoryImpl(
        private val cache: StageViewerRedisRepository
    ) : StageViewerRepository {

        override fun findByChannel(channel: String): StageViewer =
            cache.findByChannel(channel)
                .run { StageViewer(channel, this) }

        override fun merge(it: StageViewer) = cache.merge(it)
    }
}