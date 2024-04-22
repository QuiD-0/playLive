package com.quid.playLive.stage.gateway.repository.cache

import org.springframework.data.repository.CrudRepository

interface UptimeRedisRepository: CrudRepository<UptimeRedisHash, String>{
}