package com.quid.playLive.stage.gateway.api

import com.quid.playLive.member.domain.MemberDetail
import com.quid.playLive.stage.gateway.api.model.MainStageResponse
import com.quid.playLive.stage.gateway.api.model.StageInfoUpdateRequest
import com.quid.playLive.stage.usecase.FindStageInfo
import com.quid.playLive.stage.usecase.OnAir
import com.quid.playLive.stage.usecase.UpdateStageInfo
import org.springframework.data.domain.Page
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
    private val find: FindStageInfo,
    private val updateStageInfo: UpdateStageInfo,
    private val onAir: OnAir
) {

    @GetMapping("/list")
    fun list(pageable: Pageable): Page<MainStageResponse> = find.mainStageList(pageable)


    @GetMapping("/{channel}")
    fun getStageInfo(@PathVariable channel: String) {
        find.byChannel("channel")
    }

    @PutMapping
    fun updateChannelStageInfo(@RequestBody request: StageInfoUpdateRequest,
                               @AuthenticationPrincipal member: MemberDetail) {
        updateStageInfo(request, member)
    }

    @GetMapping("/uptime/{channel}")
    fun getUptime(@PathVariable channel: String) =
        onAir.findBy(channel).toUptime()

}