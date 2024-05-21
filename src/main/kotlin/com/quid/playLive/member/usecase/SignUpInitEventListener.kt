package com.quid.playLive.member.usecase

import com.quid.playLive.member.domain.MemberAuthority
import com.quid.playLive.member.gateway.repository.MemberAuthorityRepository
import com.quid.playLive.stage.domain.StageInfo
import com.quid.playLive.stage.gateway.repository.StageInfoRepository
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

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    fun onSignUpInitEvent(event: SignUpInitEvent) {
        val memberSeq = event.memberSeq
        authorityRepository.save(MemberAuthority(memberSeq))
        stageInfo.save(StageInfo(memberSeq))
    }
}

data class SignUpInitEvent(
    val memberSeq: Long,
) : ApplicationEvent(memberSeq) {
    override fun getSource(): Long = memberSeq
}