package com.quid.playLive.stage.usecase

import com.quid.playLive.stage.gateway.repository.StreamKeyRepository
import org.springframework.stereotype.Component

@Component
class ChannelToStreamKey(
    private val streamKey: StreamKeyRepository
) {
    fun channelToStreamKey(channel: String): String =
        if (channel.endsWith(".ts")) {
            channel.split(".")[0]
                .let { streamKey.findByChannel(it) + ".ts" }
        } else {
            streamKey.findByChannel(channel) + ".m3u8"
        }
}