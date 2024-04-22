package com.quid.playLive.member.gateway.repository.jpa

import org.springframework.data.jpa.repository.JpaRepository

interface JpaMemberRepository : JpaRepository<MemberEntity, Long> {
    fun findByUsername(channel: String): MemberEntity?
    fun existsByUsername(username: String): Boolean
    fun findByStreamKey(streamKey: String): MemberEntity?
}