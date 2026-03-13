package com.quid.playLive.member.gateway.repository.jpa

import org.springframework.data.jpa.repository.JpaRepository

interface JpaAuthorityRepository : JpaRepository<MemberAuthorityEntity, Long> {
    fun findByMemberId(memberId: Long): List<MemberAuthorityEntity>
    fun save(authorityMember: MemberAuthorityEntity): MemberAuthorityEntity
}