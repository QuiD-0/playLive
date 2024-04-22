package com.quid.playLive.global.web

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class WebController {

    @GetMapping("/live/{channel}")
    fun getChannel(@PathVariable channel: String, model: Model): String {
        model.addAttribute("channel", channel)
        return "live"
    }
}