package com.quid.playLive.stage.gateway.repository.redis

import com.quid.playLive.stage.domain.StageViewer

data class StageViewerRedisHash(
    val channel: String,
    val clientUuid: String,
) {
    val keyString: String
        get() = "$channel:$clientUuid"

    constructor(stageViewer: StageViewer) : this(
        stageViewer.channel,
        stageViewer.clientUuid
    )
}
