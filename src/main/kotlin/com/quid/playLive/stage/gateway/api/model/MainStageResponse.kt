package com.quid.playLive.stage.gateway.api.model

import com.quid.playLive.global.image.Avatar
import com.quid.playLive.global.image.Thumbnail

data class MainStageResponse(
    val username: String,
    val nickName: String,
    val avatar: ByteArray,
    val title: String,
    val thumbnail: ByteArray,
) {
    constructor(username: String, nickName: String, avatar: String, title: String, thumbnail: String) :
        this(username, nickName, Avatar(avatar).toImage(), title, Thumbnail(thumbnail).toImage())
}
