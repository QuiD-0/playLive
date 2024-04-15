package com.quid.playLive.stage.usecase

import org.springframework.core.io.FileSystemResource
import org.springframework.core.io.Resource
import org.springframework.stereotype.Service

interface ResourceConnector {
    operator fun invoke(channel: String, path: String): Resource

    @Service
    class HlsPathResourceConnector : ResourceConnector {
        override fun invoke(channel: String, path: String): Resource =
            if (channel.endsWith(".ts")) {
                FileSystemResource("$path/$channel")
            } else {
                FileSystemResource("$path/$channel.m3u8")
            }
    }
}