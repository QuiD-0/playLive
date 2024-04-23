package com.quid.playLive.stage.gateway.repository.cache

import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository

interface OnAirRedisRepository: CrudRepository<OnAirRedisHash, String>, PagingAndSortingRepository<OnAirRedisHash, String>{
}