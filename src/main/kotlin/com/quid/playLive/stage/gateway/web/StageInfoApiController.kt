package com.quid.playLive.stage.gateway.web

import com.quid.playLive.stage.usecase.FindStageInfo
import com.quid.playLive.stage.usecase.Uptime
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/stage/info")
class StageInfoApiController(
    private val find: FindStageInfo,
    private val uptime: Uptime
) {

    @GetMapping("/{channel}")
    fun getStageInfo(@PathVariable channel: String) {
        find.byChannel("channel")
    }

    @GetMapping("/uptime/{channel}")
    fun getUptime(@PathVariable channel: String) {
        val start = uptime.findBy(channel)
    }

}