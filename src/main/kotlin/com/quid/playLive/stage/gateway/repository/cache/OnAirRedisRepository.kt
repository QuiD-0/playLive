package com.quid.playLive.stage.gateway.repository.cache

import org.springframework.data.repository.CrudRepository

interface OnAirRedisRepository: CrudRepository<OnAirRedisHash, String>{
}