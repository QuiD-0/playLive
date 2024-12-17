package com.quid.playLive.member.usecase

import com.quid.playLive.member.domain.MemberAuthority
import com.quid.playLive.member.gateway.repository.MemberAuthorityRepository
import com.quid.playLive.stage.domain.StageInfo
import com.quid.playLive.stage.gateway.repository.StageInfoRepository
import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationEvent
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

@Component
class SignUpInitEventListener(
    private val authorityRepository: MemberAuthorityRepository,
    private val stageInfo: StageInfoRepository,
) {
    val log = LoggerFactory.getLogger(this::class.java)!!

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    fun onSignUpInitEvent(event: SignUpInitEvent) {
        log.info("SignUpInitEvent: {}", event.memberId)

        val memberId = event.memberId
        authorityRepository.save(MemberAuthority(memberId))
        stageInfo.save(StageInfo(memberId))
    }
}

data class SignUpInitEvent(
    val memberId: Long,
) : ApplicationEvent(memberId) {
    override fun getSource(): Long = memberId
}
