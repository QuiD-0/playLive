package com.quid.playLive.chat.infra.api

import com.quid.playLive.chat.service.ChatInfoService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/chat")
class ChatInfoController(
    private val chat: ChatInfoService
) {

    @GetMapping("/roomId/{channel}")
    fun getRoomId(@PathVariable channel: String): String {
        return chat.getRoomId(channel)
    }
}
