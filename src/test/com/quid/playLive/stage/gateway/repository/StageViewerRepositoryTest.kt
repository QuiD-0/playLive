package com.quid.playLive.stage.gateway.repository

import com.quid.playLive.stage.domain.StageViewer
import org.junit.jupiter.api.Test

class StageViewerRepositoryTest {

    private val repository = StageViewerRepository.InMemoryStageViewerRepository()

    @Test
    fun emptyChannelWillReturnEmptyViewer() {
        val stageViewer = repository.findByChannel("channel")
        assert(stageViewer.viewers.isEmpty())
    }

    @Test
    fun merge() {
        val channel = "channel"
        val uuid = "uuid"
        val stageViewer = StageViewer(channel, uuid)
        repository.merge(stageViewer)

        val result = repository.findByChannel(channel)
        assert(result.viewers.size == 1)
    }

}