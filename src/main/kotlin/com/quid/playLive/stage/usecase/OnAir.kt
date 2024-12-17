package com.quid.playLive.stage.usecase

import com.quid.playLive.stage.gateway.repository.StageInfoRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

interface OnAir {

    fun start(channel: String)
    fun stop(channel: String)
    fun findStartDateTimeBy(channel: String): LocalDateTime
    fun isOnAir(channel: String): Boolean

    @Service
    class StageOnAir(
        private val repository: StageInfoRepository
    ) : OnAir {
        override fun start(channel: String) {
            val stageInfo = repository.findByChannel(channel)
            repository.save(stageInfo.onAir())
        }

        override fun stop(channel: String) {
            val stageInfo = repository.findByChannel(channel)
            repository.save(stageInfo.offAir())
        }

        override fun findStartDateTimeBy(channel: String): LocalDateTime =
            repository.findByChannel(channel).onAirInfo.liveStartDateTime
                ?: throw IllegalArgumentException("Channel $channel is not on air")

        override fun isOnAir(channel: String): Boolean =
            repository.findByChannel(channel).onAirInfo.isLiveOn
    }
}
