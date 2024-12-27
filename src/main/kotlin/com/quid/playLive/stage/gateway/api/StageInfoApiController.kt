package com.quid.playLive.stage.gateway.api

import com.quid.playLive.global.api.Success
import com.quid.playLive.stage.usecase.OnAirService
import com.quid.playLive.stage.usecase.StageInfoService
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
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

    @GetMapping("/uptime/{channel}")
    fun getUptime(@PathVariable channel: String) =
        Success { onAir.findStartDateTimeBy(channel) }
}
