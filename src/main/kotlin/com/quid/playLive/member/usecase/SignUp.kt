package com.quid.playLive.member.usecase

import com.quid.playLive.member.domain.Member
import com.quid.playLive.member.gateway.repository.MemberRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

fun interface SignUp {
    operator fun invoke(member: Member)

    @Service
    class SignUpUseCase(
        private val userRepository: MemberRepository,
        private val passwordEncoder: PasswordEncoder
    ) : SignUp {

        override fun invoke(member: Member) {
            isUserExist(member)
                ?.let { throw IllegalArgumentException("Username already exists") }
                ?: passwordEncoder.encode(member.password)
                    .let { member.encodePassword(it) }
                    .let { userRepository.save(it) }
        }

        private fun isUserExist(user: Member) =
            takeIf { userRepository.existsByUsername(user.username) }
    }
}