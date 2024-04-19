package com.quid.playLive.stage.gateway.repository

import com.quid.playLive.member.gateway.repository.MemberRepository
import com.quid.playLive.stage.gateway.repository.cache.StreamKeyRedisRepository
import org.springframework.stereotype.Repository

interface StreamKeyRepository {
    fun findByChannel(channel: String): String

    @Repository
    class StreamKeyRepositoryImpl(
        private val cache: StreamKeyRedisRepository,
        private val user: MemberRepository
    ) : StreamKeyRepository {

        override fun findByChannel(channel: String): String =
            cache.findByChannel(channel)
                ?.streamKey
                ?: user.findByChannel(channel).streamKey
    }
}