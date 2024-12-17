package com.quid.playLive.stage.domain

import java.util.*

@JvmInline
value class StreamKey(
    val value: String = UUID.randomUUID().toString().replace("-", "")
) {
    fun renew() = StreamKey()
}
