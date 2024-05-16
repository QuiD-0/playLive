package com.quid.playLive.global.web

import com.quid.playLive.member.usecase.FindMember
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class WebController(
    private val member: FindMember
) {

    @GetMapping("/")
    fun index(): String {
        return "home"
    }

    @GetMapping("/live/{channel}")
    fun getChannel(@PathVariable channel: String): String =
        if (member.exists(channel)) {
            "live"
        } else {
            "channel-not-found"
        }
}