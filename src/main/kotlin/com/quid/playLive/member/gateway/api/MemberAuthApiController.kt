package com.quid.playLive.member.gateway.api

import com.quid.playLive.global.api.Success
import com.quid.playLive.member.domain.MemberDetail
import com.quid.playLive.member.gateway.api.model.MemberInfoResponse
import com.quid.playLive.member.gateway.api.model.UpdateProfileRequest
import com.quid.playLive.member.usecase.LogOut
import com.quid.playLive.member.usecase.UpdateProfile
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth/member")
class MemberAuthApiController(
    private val logOut: LogOut,
    private val updateProfile: UpdateProfile
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
    ) = Success { updateProfile.invoke(memberDetail.member, request) }
}
