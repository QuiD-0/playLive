package com.quid.playLive.stage.usecase

import com.quid.playLive.member.domain.MemberDetail
import com.quid.playLive.stage.gateway.api.model.StageInfoUpdateRequest
import com.quid.playLive.stage.gateway.repository.StageInfoRepository
import org.springframework.stereotype.Service

interface UpdateStageInfo {
    operator fun invoke(request: StageInfoUpdateRequest, member: MemberDetail)

    @Service
    class UpdateStageInfoUseCase(
        private val stageInfo: StageInfoRepository
    ) : UpdateStageInfo {
        override fun invoke(request: StageInfoUpdateRequest, member: MemberDetail) {
            require(request.isSameChannel(member)) { "Unauthorized" }
            stageInfo.findByMemberSeq(member.id).toDomain()
                .run { this.updateTitleAndDescription(request) }
                .let { stageInfo.save(it) }
        }
    }
}