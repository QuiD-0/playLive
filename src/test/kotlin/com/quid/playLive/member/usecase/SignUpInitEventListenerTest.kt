package com.quid.playLive.member.usecase

import com.quid.playLive.fixture.MemberFixture
import com.quid.playLive.member.gateway.repository.MemberRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.transaction.AfterTransaction
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@ActiveProfiles("test")
class SignUpInitEventListenerTest(
    @Autowired
    private val signUp: SignUp,
    @Autowired
    private val memberRepository: MemberRepository,
) {

    private val member = MemberFixture.member

    @Test
    @Transactional
    @Rollback(false)
    fun signUpSuccess() {
        assertDoesNotThrow { signUp(member) }
    }

    @AfterTransaction
    fun afterTransaction() {
        val savedMember = memberRepository.findByUsername(member.username)
        assertNotNull(savedMember)
        assertEquals(member.username, savedMember.username)
        assertNotNull(savedMember.id)
        println("savedMember.id = ${savedMember.id}")
    }


}