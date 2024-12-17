package com.quid.playLive.member.usecase

import com.quid.playLive.member.domain.MemberDetail
import com.quid.playLive.member.gateway.repository.MemberAuthorityRepository
import com.quid.playLive.member.gateway.repository.MemberRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserAuthService(
    private val member: MemberRepository,
    private val authority: MemberAuthorityRepository
) : UserDetailsService{
    override fun loadUserByUsername(username: String): UserDetails {
        val user = member.findByUsername(username)
        val authority = authority.findByUserId(user.id!!)
        return MemberDetail(user, authority)
    }

}
