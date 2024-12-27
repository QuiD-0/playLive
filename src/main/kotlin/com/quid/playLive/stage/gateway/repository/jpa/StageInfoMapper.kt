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
        isLiveOn = stageInfo.onAirInfo.isLiveOn,
        liveStartDateTime = stageInfo.onAirInfo.liveStartDateTime
    )
}

fun toDomain(stageInfoEntity: StageInfoEntity): StageInfo {
    return StageInfo(
        id = stageInfoEntity.id,
        memberId = stageInfoEntity.memberId,
        title = stageInfoEntity.title,
        description = stageInfoEntity.description,
        streamKey = UUID(stageInfoEntity.streamKey),
        onAirInfo = OnAirInfo(stageInfoEntity.isLiveOn, stageInfoEntity.liveStartDateTime)
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
