package com.quid.playLive.stage.usecase

import com.quid.playLive.member.domain.MemberDetail
import com.quid.playLive.member.gateway.repository.MemberRepository
import com.quid.playLive.stage.gateway.api.model.MainStageResponse
import com.quid.playLive.stage.gateway.api.model.StageInfoResponse
import com.quid.playLive.stage.gateway.api.model.StageInfoUpdateRequest
import com.quid.playLive.stage.gateway.repository.StageInfoRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

interface StageInfoService {
    fun byChannel(channel: String) : StageInfoResponse
    fun mainStageList(pageable: Pageable): Page<MainStageResponse>
    fun update(request: StageInfoUpdateRequest, member: MemberDetail)

    @Service
    class StageInfoServiceImpl(
        private val stageInfo: StageInfoRepository,
        private val member: MemberRepository,
    ) : StageInfoService {
        override fun byChannel(channel: String): StageInfoResponse {
            val member = member.findByChannel(channel)
            val stageInfo = stageInfo.findByMemberId(member.id!!)

            return StageInfoResponse(member, stageInfo)
        }

        override fun mainStageList(pageable: Pageable): Page<MainStageResponse> =
            stageInfo.findAllLiveChannel(pageable)

        override fun update(request: StageInfoUpdateRequest, member: MemberDetail) {
            stageInfo.findByMemberId(member.id)
                .updateTitleAndDescription(request)
                .let { stageInfo.save(it) }
        }
    }
}
