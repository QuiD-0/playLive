package com.quid.playLive.stage.usecase

import org.springframework.core.io.FileSystemResource
import org.springframework.core.io.Resource
import org.springframework.stereotype.Service

interface ResourceConnector {
    operator fun invoke(channel: String, path: String): Resource

    @Service
    class HlsPathResourceConnector(
        private val converter: ChannelToStreamKey
    ) : ResourceConnector {
        override fun invoke(channel: String, path: String): Resource = converter.channelToStreamKey(channel)
            .let { "$path/$it" }
            .let { FileSystemResource(it) }
    }
}