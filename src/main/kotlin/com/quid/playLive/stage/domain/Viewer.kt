package com.quid.playLive.stage.domain

import java.time.LocalDateTime

data class Viewer(
    val uuid: String,
    val datetime: LocalDateTime = LocalDateTime.now()
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Viewer

        return uuid == other.uuid
    }

    override fun hashCode(): Int {
        return uuid.hashCode()
    }
}