package com.quid.playLive.global

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "hls")
data class ResourcePath(
    val video: String,
    val radio: String,
    val thumbnail: String,
    val basePath: String,
)
