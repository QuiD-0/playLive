package com.quid.playLive.stage.usecase

import com.quid.playLive.member.gateway.repository.MemberRepository
import com.quid.playLive.stage.domain.StageInfo
import com.quid.playLive.stage.gateway.repository.StageInfoRepository
import org.springframework.stereotype.Service

interface FindStageInfo {
    fun byChannel(channel: String) : StageInfo

    @Service
    class FindStageInfoImpl(
        private val repository: StageInfoRepository,
        private val member: MemberRepository
    ) : FindStageInfo {
        override fun byChannel(channel: String): StageInfo =
            member.findByChannel(channel).id
                .let { repository.findById(it!!) }
    }
}