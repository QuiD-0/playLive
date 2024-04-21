package com.quid.playLive.member.gateway.repository

import com.quid.playLive.member.domain.MemberAuthority
import com.quid.playLive.member.gateway.repository.jpa.JpaAuthorityRepository
import com.quid.playLive.member.gateway.repository.jpa.MemberAuthorityEntity
import org.springframework.stereotype.Repository

interface MemberAuthorityRepository {
    fun findByUserSeq(id: Long): List<MemberAuthority>
    fun save(memberAuthority: MemberAuthority): MemberAuthority

    @Repository
    class MemberAuthorityRepositoryImpl(
        private val authority: JpaAuthorityRepository
    ) : MemberAuthorityRepository {
        override fun findByUserSeq(id: Long): List<MemberAuthority> =
            authority.findAll().map { it.toDomain() }

        override fun save(memberAuthority: MemberAuthority): MemberAuthority =
            authority.save(MemberAuthorityEntity(memberAuthority)).toDomain()
    }
}