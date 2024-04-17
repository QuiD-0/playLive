package com.quid.playLive.stage.gateway.repository

import com.quid.playLive.stage.gateway.repository.cache.InMemoryStreamKey
import com.quid.playLive.member.gateway.repository.MemberRepository
import org.springframework.stereotype.Repository

interface StreamKeyRepository {
    fun findByChannel(channel: String): String

    @Repository
    class StreamKeyRepositoryImpl(
        private val cache: InMemoryStreamKey,
        private val user: MemberRepository
    ) : StreamKeyRepository {

        override fun findByChannel(channel: String): String =
            cache.findByChannel(channel)
                ?: user.findByChannel(channel).streamKey
                    .also { cache.merge(channel, it) }
    }
}