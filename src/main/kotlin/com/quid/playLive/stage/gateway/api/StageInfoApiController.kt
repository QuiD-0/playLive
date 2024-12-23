package com.quid.playLive.stage.gateway.api

import com.quid.playLive.global.api.Success
import com.quid.playLive.member.domain.MemberDetail
import com.quid.playLive.stage.gateway.api.model.StageInfoUpdateRequest
import com.quid.playLive.stage.usecase.OnAirService
import com.quid.playLive.stage.usecase.StageInfoService
import org.springframework.data.domain.Pageable
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/stage/info")
class StageInfoApiController(
    private val stageInfo: StageInfoService,
    private val onAir: OnAirService
) {

    @GetMapping("/list")
    fun list(pageable: Pageable) =
        Success { stageInfo.mainStageList(pageable) }

    @GetMapping("/{channel}")
    fun getStageInfo(@PathVariable channel: String) =
        Success { stageInfo.byChannel(channel) }

    @PutMapping
    fun updateChannelStageInfo(
        @RequestBody request: StageInfoUpdateRequest,
        @AuthenticationPrincipal member: MemberDetail
    ) = Success { stageInfo.update(request, member) }

    @GetMapping("/uptime/{channel}")
    fun getUptime(@PathVariable channel: String) =
        Success { onAir.findStartDateTimeBy(channel) }

    @GetMapping("/me")
    fun getMyStageInfo(@AuthenticationPrincipal member: MemberDetail) =
        Success { stageInfo.byChannel(member.member.username) }

}
