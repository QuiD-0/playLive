package com.quid.playLive.stage.gateway.api.model

import com.quid.playLive.global.image.Avatar
import com.quid.playLive.global.image.Thumbnail

data class MainStageResponse(
    val username: String,
    val nickName: String,
    val title: String,
) {
    val avatar: ByteArray by lazy { Avatar(username).toImage() }
    val thumbnail: ByteArray by lazy { Thumbnail(username).toImage() }
}
