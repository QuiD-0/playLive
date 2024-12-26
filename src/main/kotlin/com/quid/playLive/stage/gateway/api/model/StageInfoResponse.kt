package com.quid.playLive.stage.gateway.api.model

import com.quid.playLive.member.domain.Member
import com.quid.playLive.stage.domain.StageInfo
import com.quid.playLive.stage.domain.StreamKey
import java.time.LocalDateTime

data class StageInfoResponse(
    val nickname: String,
    val title: String,
    val description: String,
    val avatar: String,
    val startDateTime: LocalDateTime,
    val streamKey: StreamKey
) {
    constructor(member:Member, stageInfo: StageInfo) : this(
        nickname = member.nickname,
        title = stageInfo.title,
        description = stageInfo.description,
        avatar = member.avatar,
        startDateTime = stageInfo.onAirInfo.liveStartDateTime!!,
        streamKey = stageInfo.streamKey
    )
}
