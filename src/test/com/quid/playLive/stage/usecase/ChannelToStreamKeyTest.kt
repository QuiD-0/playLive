package com.quid.playLive.stage.usecase

import com.quid.playLive.stage.gateway.repository.StreamKeyRepository
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class ChannelToStreamKeyTest {

    private val repository: StreamKeyRepository = Mockito.mock(StreamKeyRepository::class.java)
    private val converter = ChannelToStreamKey(repository)

    @Test
    @DisplayName("channel.ts 파일은 streamKey.ts로 변환")
    fun channelToStreamKey() {
        val channel = "channel"
        val streamKey = "streamKey"
        given(repository.findByChannel(channel)).willReturn(streamKey)

        val result = converter.channelToStreamKey("$channel.ts")

        assert(result == "$streamKey.ts")
    }

    @Test
    @DisplayName("channel 파일은 streamKey.m3u8로 변환")
    fun channelToStreamKey2() {
        val channel = "channel"
        val streamKey = "streamKey"
        given(repository.findByChannel(channel)).willReturn(streamKey)

        val result = converter.channelToStreamKey(channel)

        assert(result == "$streamKey.m3u8")
    }
}