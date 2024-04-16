package com.quid.playLive.user.domain

import java.time.LocalDateTime

data class User(
    val id: Long? = null,
    val email: String,
    val password: String,
    val nickname: String,
    val streamKey: String? = null,
    val regDate: LocalDateTime = LocalDateTime.now(),
) {
    fun assignStreamKey(streamKey: String) = copy(streamKey = streamKey)
}
