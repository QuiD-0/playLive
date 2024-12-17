package com.quid.playLive.stage.usecase

import com.quid.playLive.member.domain.MemberDetail
import com.quid.playLive.member.gateway.repository.MemberRepository
import com.quid.playLive.stage.domain.StageInfo
import com.quid.playLive.stage.gateway.api.model.MainStageResponse
import com.quid.playLive.stage.gateway.api.model.StageInfoUpdateRequest
import com.quid.playLive.stage.gateway.repository.StageInfoRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

interface StageInfoService {
    fun byChannel(channel: String) : StageInfo
    fun mainStageList(pageable: Pageable): Page<MainStageResponse>
    fun update(request: StageInfoUpdateRequest, member: MemberDetail)

    @Service
    class StageInfoServiceImpl(
        private val stageInfo: StageInfoRepository,
        private val member: MemberRepository,
    ) : StageInfoService {
        override fun byChannel(channel: String): StageInfo =
            member.findByChannel(channel).id
                .let { stageInfo.findById(it!!) }

        override fun mainStageList(pageable: Pageable): Page<MainStageResponse> =
            stageInfo.findAllLiveChannel(pageable)

        override fun update(request: StageInfoUpdateRequest, member: MemberDetail) {
            require(request.isSameChannel(member)) { "Unauthorized" }
            stageInfo.findByMemberId(member.id)
                .run { this.updateTitleAndDescription(request) }
                .let { stageInfo.save(it) }
        }
    }
}
