package com.quid.playLive.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

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