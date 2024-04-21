package com.quid.playLive.member.gateway.repository

import com.quid.playLive.member.domain.MemberAuthority
import org.springframework.stereotype.Repository

interface MemberAuthorityRepository {
    fun findByUserSeq(id: Long): List<MemberAuthority>

    @Repository
    class MemberAuthorityRepositoryImpl(
    ) : MemberAuthorityRepository {
        override fun findByUserSeq(id: Long): List<MemberAuthority> {
            return listOf(MemberAuthority(1, "ROLE_USER"))
        }
    }
}