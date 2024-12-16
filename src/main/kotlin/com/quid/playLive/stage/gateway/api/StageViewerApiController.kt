package com.quid.playLive.stage.gateway.api

import com.quid.playLive.global.api.Success
import com.quid.playLive.stage.gateway.api.model.ClientUuid
import com.quid.playLive.stage.usecase.AddStageViewer
import com.quid.playLive.stage.usecase.FindStageViewer
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/stage/viewer")
class StageViewerApiController(
    private val addViewer: AddStageViewer,
    private val viewer: FindStageViewer
) {

    @PostMapping("/{channel}")
    fun addStageViewer(@PathVariable channel: String, @RequestBody request: ClientUuid) =
        Success { addViewer(channel, request.clientUUID) }

    @GetMapping("/{channel}")
    fun getStageViewer(@PathVariable channel: String) =
        Success { viewer.byChannel(channel).count() }
}
