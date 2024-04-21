package com.quid.playLive.member.usecase

import com.quid.playLive.member.domain.Member
import com.quid.playLive.member.gateway.repository.MemberRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

fun interface SignUp {
    operator fun invoke(member: Member)

    @Service
    class SignUpUseCase(
        private val userRepository: MemberRepository,
        private val initEvent: ApplicationEventPublisher,
        private val passwordEncoder: PasswordEncoder
    ) : SignUp {

        @Transactional
        override fun invoke(member: Member) {
            checkUserExist(member)

            val savedMember = passwordEncoder.encode(member.password)
                .let { member.encodePassword(it) }
                .let { userRepository.save(it) }

            initEvent.publishEvent(SignUpInitEvent(savedMember.id!!))
        }

        private fun checkUserExist(user: Member) {
            if (userRepository.existsByUsername(user.username)) {
                throw IllegalArgumentException("Username already exists")
            }
        }
    }
}