package com.quid.playLive.stage.domain

import java.time.LocalDateTime

data class OnAirInfo(
    val isLiveOn: Boolean = false,
    val liveStartDateTime: LocalDateTime? = null
) {
    fun onAir(): OnAirInfo {
        return copy(isLiveOn = true, liveStartDateTime = LocalDateTime.now())
    }

    fun offAir(): OnAirInfo {
        return copy(isLiveOn = false, liveStartDateTime = null)
    }
}
