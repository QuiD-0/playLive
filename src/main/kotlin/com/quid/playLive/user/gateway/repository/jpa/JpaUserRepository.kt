package com.quid.playLive.user.gateway.repository.jpa

import org.springframework.data.jpa.repository.JpaRepository

interface JpaUserRepository : JpaRepository<UserEntity, Long> {
    fun findByUsername(channel: String): UserEntity?
}