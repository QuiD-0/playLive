package com.quid.playLive.stage.gateway.repository.cache

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Component

@Component
interface StageViewerRedisRepository : CrudRepository<StageViewerRedisHash, String>{
    fun findByChannel(channel: String): StageViewerRedisHash?
}