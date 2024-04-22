package com.quid.playLive.stage.domain

data class UptimeDisplay(val channel: String, val startTime: Long, val now: Long = System.currentTimeMillis()){
    fun toUptime(): String {
        val duration = (now - startTime) / 1000
        val hours = duration / 3600
        val minutes = (duration % 3600) / 60
        val seconds = duration % 60
        return "%02d:%02d:%02d".format(hours, minutes, seconds)
    }
}