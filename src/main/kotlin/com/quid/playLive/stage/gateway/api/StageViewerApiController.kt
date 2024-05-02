package com.quid.playLive.stage.gateway.api

import com.quid.playLive.global.api.Success
import com.quid.playLive.stage.usecase.AddStageViewer
import com.quid.playLive.stage.usecase.FindStageViewer
import jakarta.servlet.http.HttpSession
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/stage/viewer")
class StageViewerApiController(
    private val addViewer: AddStageViewer,
    private val viewer: FindStageViewer
) {

    @PostMapping("/{channel}")
    fun addStageViewer(@PathVariable channel: String, session: HttpSession) =
        Success { addViewer(channel, session.id) }

    @GetMapping("/{channel}")
    fun getStageViewer(@PathVariable channel: String) =
        Success { viewer.byChannel(channel).count() }
}