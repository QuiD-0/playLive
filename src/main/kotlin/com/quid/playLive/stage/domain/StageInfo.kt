package com.quid.playLive.stage.domain

import com.quid.playLive.stage.gateway.api.model.StageInfoUpdateRequest

data class StageInfo(
    val id: Long? = null,
    val memberId: Long,
    val title: String = "",
    val description: String = "",
    val streamKey: StreamKey = StreamKey(),
    val onAirInfo: OnAirInfo = OnAirInfo(),
) {
    constructor(userId: Long) : this(null, userId)

    fun updateTitleAndDescription(request: StageInfoUpdateRequest) =
        copy(title = request.title, description = request.description)

    fun onAir(): StageInfo {
        return copy(onAirInfo = onAirInfo.onAir())
    }

    fun offAir(): StageInfo {
        return copy(onAirInfo = onAirInfo.offAir())
    }
}
