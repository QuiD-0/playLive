package com.quid.playLive.member.usecase

import com.quid.playLive.member.domain.Member
import com.quid.playLive.member.gateway.api.model.UpdateProfileRequest
import com.quid.playLive.member.gateway.repository.MemberRepository
import org.springframework.stereotype.Service

@Service
class UpdateProfile(
    private val repository: MemberRepository
) {
    fun invoke(member: Member, request: UpdateProfileRequest) {
        member.updateProfile(request.nickname)
            .let { repository.save(it) }
    }
}
