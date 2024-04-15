package com.quid.playLive.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class HlsInfo {

    @Bean
    fun hlsInfo() = LivePath(
        video = "/home/ubuntu/hls",
        radio = "/home/ubuntu/radio"
    )
}

data class LivePath(
    val video: String,
    val radio: String
)