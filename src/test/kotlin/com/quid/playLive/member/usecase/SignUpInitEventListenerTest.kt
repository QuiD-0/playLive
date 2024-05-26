package com.quid.playLive.member.usecase

import com.quid.playLive.fixture.MemberFixture
import com.quid.playLive.member.gateway.repository.MemberRepository
import com.quid.playLive.stage.gateway.repository.StageInfoRepository
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
    private val stageInfoRepository: StageInfoRepository
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
        val stage = stageInfoRepository.findByMemberSeq(member.id!!)
        assertNotNull(stage)
    }


}