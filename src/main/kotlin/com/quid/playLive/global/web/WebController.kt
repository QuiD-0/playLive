package com.quid.playLive.global.web

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class WebController {

    @GetMapping("/")
    fun index(): String {
        return "home"
    }

    @GetMapping("/live/{channel}")
    fun getChannel(@PathVariable channel: String): String {
        return "live"
    }
}