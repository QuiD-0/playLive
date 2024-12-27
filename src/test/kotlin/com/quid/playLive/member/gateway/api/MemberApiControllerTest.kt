package com.quid.playLive.member.gateway.api

import com.fasterxml.jackson.databind.ObjectMapper
import com.quid.playLive.fixture.any
import com.quid.playLive.fixture.mvc
import com.quid.playLive.fixture.securityMvc
import com.quid.playLive.global.api.UnauthorizedException
import com.quid.playLive.member.gateway.api.model.LogInRequest
import com.quid.playLive.member.gateway.api.model.SignUpRequest
import com.quid.playLive.member.usecase.FindMember
import com.quid.playLive.member.usecase.LogIn
import com.quid.playLive.member.usecase.SignUp
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.web.context.WebApplicationContext

@WebMvcTest(MemberApiController::class)
class MemberApiControllerTest {

    @MockBean
    private lateinit var signUp: SignUp

    @MockBean
    private lateinit var logIn: LogIn

    @MockBean
    private lateinit var findMember: FindMember

    @Autowired
    private lateinit var webContext: WebApplicationContext

    private val mockMvc: MockMvc by lazy {
        mvc(MemberApiController(signUp, logIn, findMember))
    }

    private val securityMvc: MockMvc by lazy {
        securityMvc(webContext)
    }

    @Test
    @DisplayName("회원가입")
    fun register() {
        val request = SignUpRequest("username", "password", "user@mail.com", "channel")

        mockMvc.perform(
            post("/api/member/register").contentType(MediaType.APPLICATION_JSON)
                .content(ObjectMapper().writeValueAsString(request))
        ).andExpect(
            status().isOk
        )
    }

    @Test
    @DisplayName("중복된 사용자 이름")
    fun duplicatedUsername() {
        val request = SignUpRequest("username", "password", "user@mail.com", "channel")

        given(signUp(any())).willThrow(IllegalArgumentException("User already exists"))

        mockMvc.perform(
            post("/api/member/register").contentType(MediaType.APPLICATION_JSON)
                .content(ObjectMapper().writeValueAsString(request))
        ).andExpect(
            status().is4xxClientError
        )
    }

    @Test
    @DisplayName("이메일 형식이 잘못된 경우")
    fun emailNotValid() {
        mockMvc.perform(
            post("/api/member/register").contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"username\",\"password\":\"password\",\"email\":\"test\",\"nickname\":\"channel\"}")
        ).andExpect(
            status().is5xxServerError
        )
    }

    @Test
    @DisplayName("로그인")
    fun login() {
        val request = LogInRequest("username", "password")

        mockMvc.perform(
            post("/api/member/login").contentType(MediaType.APPLICATION_JSON)
                .content(ObjectMapper().writeValueAsString(request))
        ).andExpect(
            status().isOk
        )
    }

    @Test
    @DisplayName("로그인 실패")
    fun loginFail() {
        val request = LogInRequest("notExist", "password")

        given(logIn(any(), any())).willThrow(UnauthorizedException("Invalid username or password"))

        mockMvc.perform(
            post("/api/member/login").contentType(MediaType.APPLICATION_JSON)
                .content(ObjectMapper().writeValueAsString(request))
        ).andExpect(
            status().isUnauthorized
        )
    }

    @Test
    @DisplayName("사용자 이름 중복 확인")
    fun checkAvailable() {
        mockMvc.perform(
            get("/api/member/check-available/username").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
            status().isOk
        )
    }

    @Test
    @DisplayName("사용자 이름 중복 확인 - 사용자 이름 중복")
    fun checkAvailableDuplicated() {
        given(findMember.exists(any())).willReturn(true)

        val result = mockMvc.perform(
            get("/api/member/check-available/username").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
            status().isOk
        ).andReturn()

        val response = ObjectMapper().readTree(result.response.contentAsString)["message"].asBoolean()
        assertEquals(response, true)
    }

    @Test
    @DisplayName("사용자 이름 중복 확인 - 사용자 이름 중복 아님")
    fun checkAvailableNotDuplicated() {
        given(findMember.exists(any())).willReturn(false)

        val result = mockMvc.perform(
            get("/api/member/check-available/username").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
            status().isOk
        ).andReturn()

        val response = ObjectMapper().readTree(result.response.contentAsString)["message"].asBoolean()
        assertEquals(response, false)
    }

}
