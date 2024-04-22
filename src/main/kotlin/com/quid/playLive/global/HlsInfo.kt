package com.quid.playLive.global

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class HlsInfo {

    @Bean
    fun livePath() = ResourcePath(
        video = "/home/ubuntu/hls",
        radio = "/home/ubuntu/radio"
    )
}

data class ResourcePath(
    val video: String,
    val radio: String
)