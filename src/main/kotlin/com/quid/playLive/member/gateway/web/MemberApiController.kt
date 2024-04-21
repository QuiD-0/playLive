package com.quid.playLive.member.gateway.web

import com.quid.playLive.member.domain.MemberDetail
import com.quid.playLive.member.gateway.web.model.LogInRequest
import com.quid.playLive.member.gateway.web.model.SignUpRequest
import com.quid.playLive.member.usecase.LogIn
import com.quid.playLive.member.usecase.LogOut
import com.quid.playLive.member.usecase.SignUp
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/member")
class MemberApiController(
    private val signUp: SignUp,
    private val logIn: LogIn,
    private val logOut: LogOut
) {

    @PostMapping("/register")
    fun register(@RequestBody request: SignUpRequest) =
        signUp(request.toMember())

    @PostMapping("/login")
    fun login(@RequestBody request: LogInRequest) =
        logIn(request.username, request.password)

    @PostMapping("/logout")
    fun logout(@AuthenticationPrincipal memberDetail: MemberDetail) {
        logOut(memberDetail.username)
    }
}