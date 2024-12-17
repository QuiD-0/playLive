package com.quid.playLive.stage.gateway.repository.jpa

import com.quid.playLive.stage.domain.OnAirInfo
import com.quid.playLive.stage.domain.StageInfo
import com.quid.playLive.stage.domain.StreamKey

fun toEntity(stageInfo: StageInfo): StageInfoEntity {
    return StageInfoEntity(
        id = stageInfo.id,
        memberId = stageInfo.memberId,
        title = stageInfo.title,
        description = stageInfo.description,
        streamKey = stageInfo.streamKey.value,
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
        streamKey = StreamKey(stageInfoEntity.streamKey),
        onAirInfo = OnAirInfo(stageInfoEntity.isLiveOn, stageInfoEntity.liveStartDateTime)
    )
}
