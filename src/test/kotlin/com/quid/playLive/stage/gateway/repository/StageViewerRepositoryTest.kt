package com.quid.playLive.stage.gateway.repository

import com.quid.playLive.stage.domain.StageViewer
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.Mockito.mock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class StageViewerRepositoryTest {

    private val repository = mock(StageViewerRepository::class.java)

    @Test
    @DisplayName("빈 채널은 빈 뷰어를 반환")
    fun emptyChannelWillReturnEmptyViewer() {
        given(repository.findByChannel("channel"))
            .willReturn(StageViewer("channel", mutableSetOf()))

        val stageViewer = repository.findByChannel("channel")
        assert(stageViewer.viewers.isEmpty())
    }

    @Test
    @DisplayName("뷰어 추가")
    fun merge() {
        val channel = "channel"
        val stageViewer = StageViewer(channel, "new user")
        given(repository.findByChannel(channel))
            .willReturn(stageViewer)

        repository.save(stageViewer)

        val result = repository.findByChannel(channel)
        assert(result.viewers.size == 1)
    }

}