package com.quid.playLive.member.gateway.api

import com.fasterxml.jackson.databind.ObjectMapper
import com.quid.playLive.fixture.Fixture
import com.quid.playLive.member.domain.Member
import com.quid.playLive.member.gateway.api.model.LogInRequest
import com.quid.playLive.member.gateway.api.model.SignUpRequest
import com.quid.playLive.member.usecase.FindMember
import com.quid.playLive.member.usecase.LogIn
import com.quid.playLive.member.usecase.LogOut
import com.quid.playLive.member.usecase.SignUp
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.any
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(MemberApiController::class)
@AutoConfigureMockMvc(addFilters = false)
class MemberApiControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var signUp: SignUp

    @MockBean
    private lateinit var logIn: LogIn

    @MockBean
    private lateinit var logOut: LogOut

    @MockBean
    private lateinit var findMember: FindMember

    @Test
    @DisplayName("회원가입")
    fun register() {
        val request = SignUpRequest("username", "password", "user@mail.com", "channel")

        mockMvc.perform(
            post("/api/member/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ObjectMapper().writeValueAsString(request))
        ).andExpect(
            status().isOk
        ).andDo(
            print()
        )
    }

    @Test
    @DisplayName("중복된 사용자 이름")
    fun duplicatedUsername() {
        val request = SignUpRequest("username", "password", "user@mail.com", "channel")

        given(signUp(Fixture.any())).willThrow(IllegalArgumentException("User already exists"))

        mockMvc.perform(
            post("/api/member/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ObjectMapper().writeValueAsString(request))
        ).andExpect(
            status().is4xxClientError
        ).andDo(
            print()
        )
    }


    @Test
    @DisplayName("이메일 형식이 잘못된 경우")
    fun emailNotValid() {
        mockMvc.perform(
            post("/api/member/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"username\",\"password\":\"password\",\"email\":\"test\",\"nickname\":\"channel\"}")
        ).andExpect(
            status().is5xxServerError
        ).andDo(
            print()
        )
    }

    @Test
    @DisplayName("로그인")
    fun login() {
        val request = LogInRequest("username", "password")

        mockMvc.perform(
            post("/api/member/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ObjectMapper().writeValueAsString(request))
        ).andExpect(
            status().isOk
        ).andDo(
            print()
        )
    }

    @Test
    @DisplayName("로그아웃")
    fun logout() {
        mockMvc.perform(
            post("/api/member/logout")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
            status().isOk
        ).andDo(
            print()
        )
    }

    @Test
    @DisplayName("사용자 이름 중복 확인")
    fun checkAvailable() {
        mockMvc.perform(
            get("/api/member/check-available/username")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
            status().isOk
        ).andDo(
            print()
        )
    }

}