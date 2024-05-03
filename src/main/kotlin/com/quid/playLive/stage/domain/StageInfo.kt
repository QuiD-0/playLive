package com.quid.playLive.stage.domain

import com.quid.playLive.stage.gateway.api.model.StageInfoUpdateRequest

data class StageInfo(
    val id: Long? = null,
    val memberSeq: Long,
    val title: String = "",
    val description: String = "",
) {
    constructor(userId: Long) : this(null, userId)

    fun updateTitleAndDescription(request: StageInfoUpdateRequest) =
        copy(title = request.title, description = request.description)
}