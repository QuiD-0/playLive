package com.quid.playLive.stage.gateway.api

import com.quid.playLive.global.api.Success
import com.quid.playLive.member.domain.MemberDetail
import com.quid.playLive.stage.gateway.api.model.StageInfoUpdateRequest
import com.quid.playLive.stage.usecase.StageInfoService
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth/stage/info")
class StageInfoAuthApiController(
    private val stageInfo: StageInfoService,
) {

    @PutMapping
    fun updateChannelStageInfo(
        @RequestBody request: StageInfoUpdateRequest,
        @AuthenticationPrincipal member: MemberDetail
    ) = Success { stageInfo.update(request, member) }

    @GetMapping("/me")
    fun getMyStageInfo(@AuthenticationPrincipal member: MemberDetail) =
        Success { stageInfo.byChannel(member.member.username) }

}
