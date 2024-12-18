package com.quid.playLive.stage.gateway.repository

import com.quid.playLive.stage.domain.StageViewer
import com.quid.playLive.stage.gateway.repository.redis.StageViewerRedisHash
import com.quid.playLive.stage.gateway.repository.redis.StageViewerRedisRepository
import org.springframework.stereotype.Repository


@Repository
class StageViewerRepository(
    private val redis: StageViewerRedisRepository
) {

    fun save(it: StageViewer) {
        redis.save(StageViewerRedisHash(it))
    }

    fun findCountByChannel(channel: String): List<StageViewer> {
        return redis.findCountByChannel(channel)
    }
}
