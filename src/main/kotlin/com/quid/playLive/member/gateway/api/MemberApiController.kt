package com.quid.playLive.member.gateway.api

import com.quid.playLive.global.api.Success
import com.quid.playLive.member.domain.MemberDetail
import com.quid.playLive.member.gateway.api.model.LogInRequest
import com.quid.playLive.member.gateway.api.model.SignUpRequest
import com.quid.playLive.member.usecase.FindMember
import com.quid.playLive.member.usecase.LogIn
import com.quid.playLive.member.usecase.LogOut
import com.quid.playLive.member.usecase.SignUp
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/member")
class MemberApiController(
    private val signUp: SignUp,
    private val logIn: LogIn,
    private val logOut: LogOut,
    private val findMember: FindMember
) {

    @PostMapping("/register")
    fun register(@RequestBody request: SignUpRequest) =
        Success { signUp(request.toMember()) }

    @PostMapping("/login")
    fun login(@RequestBody request: LogInRequest) =
        Success { logIn(request.username, request.password) }

    @PostMapping("/logout")
    fun logout(@AuthenticationPrincipal memberDetail: MemberDetail) {
        Success { logOut(memberDetail.username) }
    }

    @GetMapping("/me")
    fun getMember(@AuthenticationPrincipal memberDetail: MemberDetail) =
        Success { memberDetail.member }

    @GetMapping("/check-available/{username}")
    fun checkAvailable(@PathVariable username: String) =
        Success { findMember.exists(username) }
}