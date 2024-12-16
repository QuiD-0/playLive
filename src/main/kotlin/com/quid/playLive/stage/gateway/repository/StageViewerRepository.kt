package com.quid.playLive.stage.gateway.repository

import com.quid.playLive.stage.domain.StageViewer
import com.quid.playLive.stage.gateway.repository.cache.StageViewerRedisHash
import com.quid.playLive.stage.gateway.repository.cache.StageViewerRedisRepository
import org.springframework.stereotype.Repository

interface StageViewerRepository {
    fun save(it: StageViewer)
    fun findCountByChannel(channel: String): List<StageViewer>

    @Repository
    class StageViewerRepositoryImpl(
        private val cache: StageViewerRedisRepository
    ) : StageViewerRepository {

        override fun save(it: StageViewer) {
            cache.save(StageViewerRedisHash(it))
        }

        override fun findCountByChannel(channel: String): List<StageViewer> {
            return cache.findCountByChannel(channel)
        }
    }
}
