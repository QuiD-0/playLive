package com.quid.playLive.stage.gateway.api

import com.quid.playLive.global.ResourcePath
import com.quid.playLive.stage.gateway.api.model.NginxNotifyRequest
import com.quid.playLive.stage.usecase.ResourceConnector
import com.quid.playLive.stage.usecase.Uptime
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/stage")
class StageApiController(
    private val path: ResourcePath,
    private val uptime: Uptime,
    private val liveStream: ResourceConnector,
) {
    @GetMapping("/live/{channel}")
    fun getLive(@PathVariable channel: String) = liveStream(channel, path.video)

    @GetMapping("/radio/{channel}")
    fun getRadio(@PathVariable channel: String) = liveStream(channel, path.radio)

    @GetMapping("/check/{channel}")
    fun checkLive(@PathVariable channel: String) = uptime.exists(channel)

    @PostMapping("/play", consumes = ["application/x-www-form-urlencoded"])
    fun play(request: NginxNotifyRequest) {
        uptime.start(request.name!!)
    }

    @PostMapping("/play_done", consumes = ["application/x-www-form-urlencoded"])
    fun playDone(request: NginxNotifyRequest) {
        uptime.stop(request.name!!)
    }

}