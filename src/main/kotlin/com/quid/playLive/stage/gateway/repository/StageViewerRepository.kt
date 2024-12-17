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
        private val redis: StageViewerRedisRepository
    ) : StageViewerRepository {

        override fun save(it: StageViewer) {
            redis.save(StageViewerRedisHash(it))
        }

        override fun findCountByChannel(channel: String): List<StageViewer> {
            return redis.findCountByChannel(channel)
        }
    }
}
