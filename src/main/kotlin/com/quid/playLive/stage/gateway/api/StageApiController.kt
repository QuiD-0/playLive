package com.quid.playLive.stage.gateway.api

import com.quid.playLive.global.ResourcePath
import com.quid.playLive.global.api.Success
import com.quid.playLive.stage.gateway.api.model.NginxNotifyRequest
import com.quid.playLive.stage.usecase.ResourceConnector
import com.quid.playLive.stage.usecase.OnAir
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/stage")
class StageApiController(
    private val path: ResourcePath,
    private val onAir: OnAir,
    private val liveStream: ResourceConnector,
) {
    private val log = LoggerFactory.getLogger(this::class.java)

    @GetMapping("/live/{channel}")
    fun getLive(@PathVariable channel: String) = liveStream(channel, path.video)

    @GetMapping("/radio/{channel}")
    fun getRadio(@PathVariable channel: String) = liveStream(channel, path.radio)

    @GetMapping("/check/{channel}")
    fun checkLive(@PathVariable channel: String) = Success { onAir.exists(channel) }

    @PostMapping("/play", consumes = ["application/x-www-form-urlencoded"])
    fun play(request: NginxNotifyRequest) {
        log.info("play request: $request")
        onAir.start(request.name!!)
    }

    @PostMapping("/play_done", consumes = ["application/x-www-form-urlencoded"])
    fun playDone(request: NginxNotifyRequest) {
        log.info("play_done request: $request")
        onAir.stop(request.name!!)
    }

}