package com.quid.playLive.stage.gateway.web

import com.quid.playLive.config.ResourcePath
import com.quid.playLive.stage.usecase.ResourceConnector
import org.springframework.web.bind.annotation.*

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

    @PostMapping("/play", consumes = ["application/x-www-form-urlencoded"])
    fun play(request: NginxNotifyRequest) {
        println("play")
        println(request)
        //방송 시작을 알수있다.
        // uptime기록 + 알림 보내는 서비스 등등 추가
    }

    @PostMapping("/play_done", consumes = ["application/x-www-form-urlencoded"])
    fun playDone(request: NginxNotifyRequest) {
        println("play_done")
        println(request)
        //방송 종료를 알수있다.
    }

    data class NginxNotifyRequest(
        val call: String?,
        val addr: String?,
        val clientid: String?,
        val app: String?,
        val flashVer: String?,
        val swfUrl: String?,
        val tcUrl: String?,
        val pageUrl: String?,
        val name: String?
    )
}