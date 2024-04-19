package com.quid.playLive.stage.gateway.repository.cache

import com.quid.playLive.stage.domain.StageViewer
import com.quid.playLive.stage.domain.Viewer
import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash(timeToLive = 30L)
data class StageViewerRedisHash(
    @Id
    val channel: String,
    val viewers: Set<Viewer>
){
    constructor(stageViewer: StageViewer) : this(
        stageViewer.channel,
        stageViewer.viewers
    )

    fun toDomain() = StageViewer(channel, viewers.toMutableSet())
}
