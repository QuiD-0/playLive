package com.quid.playLive.stage.gateway.web

import com.quid.playLive.stage.usecase.StreamMedia
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/stage")
class StageApiController(
    @Qualifier("streamMedia.LiveStream") private val liveStream: StreamMedia,
    @Qualifier("streamMedia.RadioStream") private val radioStream: StreamMedia
) {
    @GetMapping("/live/{channel}")
    fun getLive(@PathVariable channel: String) = liveStream(channel)

    @GetMapping("/radio/{channel}")
    fun getRadio(@PathVariable channel: String) = radioStream(channel)
}