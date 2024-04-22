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
        override fun invoke(channel: String, path: String): Resource = channelToFile(channel)
//        converter.channelToStreamKey(channel)
            .let { "$path/$it" }
            .let { getFile(it, 0) }

        private fun getFile(it: String, count: Int): Resource {
            try {
                return FileSystemResource(it)
            } catch (e: Exception) {
                if (count > 3) {
                    throw Exception("File not found")
                }
                return getFile(it, count + 1)
            }
        }

        private fun channelToFile(channel: String): String {
            return if (channel.endsWith(".ts")) {
                channel.split(".")[0]
                    .let { "$it.ts" }
            } else {
                "$channel.m3u8"
            }
        }
    }
}