package com.quid.playLive.member.gateway.api

import com.quid.playLive.global.api.Success
import com.quid.playLive.global.s3.ImageUploadDto
import com.quid.playLive.member.domain.MemberDetail
import com.quid.playLive.member.gateway.api.model.AvatarResponse
import com.quid.playLive.member.gateway.api.model.MemberInfoResponse
import com.quid.playLive.member.gateway.api.model.UpdateProfileRequest
import com.quid.playLive.member.usecase.LogOut
import com.quid.playLive.member.usecase.MemberUpdate
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/auth/member")
class MemberAuthApiController(
    private val logOut: LogOut,
    private val memberUpdate: MemberUpdate
) {

    @PostMapping("/logout")
    fun logout(@AuthenticationPrincipal memberDetail: MemberDetail) {
        Success { logOut(memberDetail.username) }
    }
    @GetMapping("/me")
    fun getMember(@AuthenticationPrincipal memberDetail: MemberDetail) =
        Success { MemberInfoResponse(memberDetail.member) }

    @PutMapping
    fun updateProfile(
        @RequestBody request: UpdateProfileRequest,
        @AuthenticationPrincipal memberDetail: MemberDetail
    ) = Success { memberUpdate.profile(memberDetail.member, request) }

    @PutMapping("/avatar")
    fun updateAvatar(
        @RequestPart image: MultipartFile,
        @AuthenticationPrincipal memberDetail: MemberDetail
    ): AvatarResponse {
        return with(ImageUploadDto(image)) {
            memberUpdate.avatar(memberDetail.member, this)
            AvatarResponse(this.hashName())
        }
    }
}
