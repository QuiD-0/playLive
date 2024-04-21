package com.quid.playLive.stage.gateway.repository

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.Mockito.mock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class StreamKeyRepositoryTest {

    private val repository = mock(StreamKeyRepository::class.java)

    @Test
    @DisplayName("채널에 해당하는 스트림키를 찾는다")
    fun findByChannel() {
        val channel = "channel"
        val streamKey = "streamKey"

        given(repository.findByChannel(channel)).willReturn(streamKey)

        val result = repository.findByChannel(channel)
        assertEquals(streamKey, result)
    }

    @Test
    @DisplayName("채널에 해당하는 스트림키가 없으면 에러를 반환한다.")
    fun findByChannelNotFound() {
        val channel = "channel"

        given(repository.findByChannel(channel)).willThrow(IllegalArgumentException::class.java)

        assertThrows<IllegalArgumentException> { repository.findByChannel(channel) }
    }

}