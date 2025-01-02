package com.quid.playLive.member.usecase

import com.quid.playLive.fixture.MemberFixture
import com.quid.playLive.stage.gateway.repository.StageInfoRepository
import org.junit.jupiter.api.Assertions.assertNotNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.transaction.AfterTransaction

@SpringBootTest
@ActiveProfiles("test")
class SignUpInitEventListenerTest(
    @Autowired
    private val signUp: SignUp,
    @Autowired
    private val stageInfoRepository: StageInfoRepository
) {

    private val member = MemberFixture.member

    @AfterTransaction
    fun afterTransaction() {
        val stage = stageInfoRepository.findByMemberId(member.id!!)
        assertNotNull(stage)
    }


}
