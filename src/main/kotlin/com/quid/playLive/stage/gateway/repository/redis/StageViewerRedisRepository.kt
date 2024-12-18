package com.quid.playLive.stage.gateway.repository.redis

import com.quid.playLive.stage.domain.StageViewer
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository
import java.time.Duration

@Repository
class StageViewerRedisRepository(
    private val redisTemplate: RedisTemplate<String, String>
) {
    fun save(stageViewerRedisHash: StageViewerRedisHash) {
        redisTemplate.opsForValue().set(
            stageViewerRedisHash.keyString,
            "",
            Duration.ofSeconds(30)
        )
    }

    fun findCountByChannel(channel: String): List<StageViewer> {
        val keys = redisTemplate.keys("$channel:*")
        return keys.map {
            val split = it.split(":")
            StageViewer(split[0], split[1])
        }
    }
}
