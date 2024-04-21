package com.quid.playLive.token.gateway.repository.redis

import org.springframework.data.repository.CrudRepository

interface RedisRefreshTokenRepository : CrudRepository<UserTokenJti, String>{
}