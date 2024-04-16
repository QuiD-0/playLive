package com.quid.playLive.stage.usecase

import com.quid.playLive.config.ResourcePath
import org.springframework.core.io.Resource
import org.springframework.stereotype.Service

interface StreamMedia {
    operator fun invoke(channel: String): Resource

    @Service
    class LiveStream(
        private val path: ResourcePath,
        private val resourceConnector: ResourceConnector
    ) : StreamMedia {

        override fun invoke(channel: String): Resource = resourceConnector(channel, path.video)

    }

    @Service
    class RadioStream(
        private val path: ResourcePath,
        private val resourceConnector: ResourceConnector
    ) : StreamMedia {

        override fun invoke(channel: String): Resource = resourceConnector(channel, path.radio)
    }

}