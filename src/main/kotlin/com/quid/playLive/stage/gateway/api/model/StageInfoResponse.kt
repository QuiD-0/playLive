package com.quid.playLive.stage.gateway.api.model

import com.quid.playLive.member.domain.Member
import com.quid.playLive.stage.domain.StageInfo
import java.time.LocalDateTime

data class StageInfoResponse(
    val nickname: String,
    val title: String,
    val avatar: String,
    val startDateTime: LocalDateTime
) {
    constructor(member:Member, stageInfo: StageInfo) : this(
        nickname = member.nickname,
        title = stageInfo.title,
        avatar = member.avatar,
        startDateTime = stageInfo.onAirInfo.liveStartDateTime!!
    )
}
