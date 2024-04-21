package com.quid.playLive.member.usecase

import com.quid.playLive.member.domain.Member
import com.quid.playLive.member.domain.default
import com.quid.playLive.member.gateway.repository.MemberAuthorityRepository
import com.quid.playLive.member.gateway.repository.MemberRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

fun interface SignUp {
    operator fun invoke(member: Member)

    @Service
    class SignUpUseCase(
        private val userRepository: MemberRepository,
        private val authorityRepository: MemberAuthorityRepository,
        private val passwordEncoder: PasswordEncoder
    ) : SignUp {

        @Transactional
        override fun invoke(member: Member) {
            isUserExist(member)
                ?.let { throw IllegalArgumentException("Username already exists") }
                ?: passwordEncoder.encode(member.password)
                    .let { member.encodePassword(it) }
                    .let { userRepository.save(it) }
                    .let { authorityRepository.save(default(it.id!!)) }
        }

        private fun isUserExist(user: Member) =
            takeIf { userRepository.existsByUsername(user.username) }
    }
}