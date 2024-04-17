package com.quid.playLive.stage.gateway.repository.cache

import com.quid.playLive.stage.domain.StageViewer
import com.quid.playLive.stage.domain.Viewer
import org.springframework.stereotype.Component

@Component
class StageViewerRedisRepository(
    private val redis
) {
    fun findByChannel(channel: String): MutableSet<Viewer> {
        //todo
    }

    fun merge(it: StageViewer) {
        //todo
    }

    companion object {
        const val EXPIRED_TIME = 30L
    }
}