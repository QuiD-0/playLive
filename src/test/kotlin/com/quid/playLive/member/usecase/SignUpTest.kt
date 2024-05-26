package com.quid.playLive.member.usecase

import com.quid.playLive.fixture.MemberFixture
import com.quid.playLive.fixture.any
import com.quid.playLive.member.gateway.repository.MemberRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.mockito.BDDMockito.given
import org.mockito.Mockito.mock
import org.springframework.context.ApplicationEventPublisher
import org.springframework.security.crypto.password.PasswordEncoder

class SignUpTest {

    private val memberRepository = mock(MemberRepository::class.java)
    private val initEvent = mock(ApplicationEventPublisher::class.java)
    private val passwordEncoder = mock(PasswordEncoder::class.java)

    private val signUp = SignUp.SignUpUseCase(memberRepository, initEvent, passwordEncoder)

    @Test
    fun signUpSuccess() {
        val member = MemberFixture.member
        given(memberRepository.existsByUsername(any())).willReturn(false)
        given(passwordEncoder.encode(any())).willReturn("encoded")
        given(memberRepository.save(any())).willReturn(member)

        assertDoesNotThrow { signUp(member) }
    }
}