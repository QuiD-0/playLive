package com.quid.playLive.member.usecase

import com.quid.playLive.member.gateway.repository.MemberRepository
import org.springframework.stereotype.Service

interface FindMember {
    fun exists(username: String): Boolean

    @Service
    class FindMemberUseCase(
        private val memberRepository: MemberRepository
    ) : FindMember {
        override fun exists(username: String): Boolean =
            memberRepository.existsByUsername(username)
    }
}
