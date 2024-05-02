package com.quid.playLive.stage.domain

import com.quid.playLive.global.image.Image
import com.quid.playLive.global.image.Thumbnail
import com.quid.playLive.global.image.WaitingCurtain
import com.quid.playLive.stage.gateway.api.model.StageInfoUpdateRequest

data class StageInfo(
    val id: Long? = null,
    val memberSeq: Long,
    val title: String = "",
    val description: String = "",
    val thumbnail: Image = Thumbnail(),
    val waitingCurtain: Image = WaitingCurtain(),
) {
    constructor(userId: Long) : this(null, userId)

    fun updateWaitingCurtain(waitingCurtain: String) = copy(waitingCurtain = WaitingCurtain(waitingCurtain))
    fun updateTitleAndDescription(request: StageInfoUpdateRequest) =
        copy(title = request.title, description = request.description)
}