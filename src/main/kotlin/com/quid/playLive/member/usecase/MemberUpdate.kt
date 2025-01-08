package com.quid.playLive.member.usecase

import com.quid.playLive.global.s3.ImageUploadDto
import com.quid.playLive.global.s3.S3ImageHandler
import com.quid.playLive.member.domain.Member
import com.quid.playLive.member.gateway.api.model.UpdateProfileRequest
import com.quid.playLive.member.gateway.repository.MemberRepository
import org.springframework.stereotype.Service

@Service
class MemberUpdate(
    private val repository: MemberRepository,
    private val s3ImageHandler: S3ImageHandler
) {
    fun profile(member: Member, request: UpdateProfileRequest) {
        member.updateProfile(request.nickname).let { repository.save(it) }
    }

    fun avatar(member: Member, imageInfo: ImageUploadDto) {
        s3ImageHandler.uploadImage(imageInfo)
        member.updateAvatar(imageInfo.hashName())
            .let { repository.save(it) }
    }
}

