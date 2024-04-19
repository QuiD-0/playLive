package com.quid.playLive.stage.gateway.web

import com.quid.playLive.config.ResourcePath
import com.quid.playLive.stage.usecase.ResourceConnector
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/stage")
class StageApiController(
    private val path: ResourcePath,
    private val liveStream: ResourceConnector,
) {
    @GetMapping("/live/{channel}")
    fun getLive(@PathVariable channel: String) = liveStream(channel, path.video)

    @GetMapping("/radio/{channel}")
    fun getRadio(@PathVariable channel: String) = liveStream(channel, path.radio)
}