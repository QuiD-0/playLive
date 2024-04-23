package com.quid.playLive.stage.gateway.api.model

data class MainStageResponse(
    val memberSeq: Long,
    val nickName: String,
    val avatar: String,
    val title: String,
    val thumbnail: String,
)
