package com.quid.playLive.global

import java.util.UUID

@JvmInline
value class UUID(
    val id: String = UUID.randomUUID().toString().replace("-", "")
) {
}
