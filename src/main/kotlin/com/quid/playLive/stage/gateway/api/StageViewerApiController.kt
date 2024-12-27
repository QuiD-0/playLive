package com.quid.playLive.stage.gateway.api

import com.quid.playLive.global.api.Success
import com.quid.playLive.stage.gateway.api.model.ClientUuid
import com.quid.playLive.stage.service.StageViewerService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/stage/viewer")
class StageViewerApiController(
    private val stageViewer: StageViewerService,
) {

    @PostMapping("/{channel}")
    fun addStageViewer(@PathVariable channel: String, @RequestBody request: ClientUuid) =
        Success { stageViewer.add(channel, request.clientUUID) }

    @GetMapping("/{channel}")
    fun getStageViewer(@PathVariable channel: String) =
        Success { stageViewer.byChannel(channel).count() }
}
