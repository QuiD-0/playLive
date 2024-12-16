package com.quid.playLive.stage.usecase

import com.quid.playLive.stage.domain.StageViewer
import com.quid.playLive.stage.gateway.repository.StageViewerRepository
import org.springframework.stereotype.Service

interface AddStageViewer {
    operator fun invoke(channel: String, uuid: String)

    @Service
    class AddStageViewerImpl(
        private val repository: StageViewerRepository
    ): AddStageViewer {
        override fun invoke(channel: String, uuid: String): Unit =
              repository.save(StageViewer(channel, uuid))
    }
}
