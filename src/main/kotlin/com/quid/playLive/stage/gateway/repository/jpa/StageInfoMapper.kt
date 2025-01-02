package com.quid.playLive.stage.gateway.repository.jpa

import com.quid.playLive.global.UUID
import com.quid.playLive.stage.domain.OnAirInfo
import com.quid.playLive.stage.domain.StageInfo
import java.time.LocalDateTime

fun toEntity(stageInfo: StageInfo): StageInfoEntity {
    return StageInfoEntity(
        id = stageInfo.id,
        memberId = stageInfo.memberId,
        title = stageInfo.title,
        description = stageInfo.description,
        streamKey = stageInfo.streamKey.id,
        chatroomId = stageInfo.chatroomId.id,
        isLiveOn = stageInfo.onAirInfo.isLiveOn,
        liveStartDateTime = stageInfo.onAirInfo.liveStartDateTime
    )
}

fun toDomain(entity: StageInfoEntity): StageInfo {
    return StageInfo(
        id = entity.id,
        memberId = entity.memberId,
        title = entity.title,
        description = entity.description,
        streamKey = UUID(entity.streamKey),
        chatroomId = UUID(entity.chatroomId),
        onAirInfo = OnAirInfo(entity.isLiveOn, entity.liveStartDateTime)
    )
}

fun toHistoryEntity(stageInfo: StageInfo): OnAirHistoryEntity {
    return OnAirHistoryEntity(
        memberId = stageInfo.memberId,
        title = stageInfo.title,
        startDateTime = stageInfo.onAirInfo.liveStartDateTime!!,
        endDateTime = LocalDateTime.now()
    )
}
