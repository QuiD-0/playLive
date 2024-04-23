package com.quid.playLive.stage.usecase

import com.quid.playLive.member.gateway.repository.MemberRepository
import com.quid.playLive.stage.domain.StageInfo
import com.quid.playLive.stage.gateway.api.model.MainStageResponse
import com.quid.playLive.stage.gateway.repository.StageInfoRepository
import com.quid.playLive.stage.gateway.repository.OnAirRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

interface FindStageInfo {
    fun byChannel(channel: String) : StageInfo
    fun mainStageList(pageable: Pageable): Page<MainStageResponse>

    @Service
    class FindStageInfoImpl(
        private val stageInfo: StageInfoRepository,
        private val member: MemberRepository,
        private val onAir: OnAirRepository
    ) : FindStageInfo {
        override fun byChannel(channel: String): StageInfo =
            member.findByChannel(channel).id
                .let { stageInfo.findById(it!!) }

        override fun mainStageList(pageable: Pageable): Page<MainStageResponse> =
            onAir.findAll(pageable)
                .let { stageInfo.findAll(it) }
                .let { PageImpl(it, pageable, it.size.toLong()) }

    }
}