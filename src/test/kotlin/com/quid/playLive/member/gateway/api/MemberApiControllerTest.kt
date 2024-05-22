package com.quid.playLive.member.gateway.api

import com.fasterxml.jackson.databind.ObjectMapper
import com.quid.playLive.member.gateway.api.model.LogInRequest
import com.quid.playLive.member.gateway.api.model.SignUpRequest
import com.quid.playLive.member.usecase.FindMember
import com.quid.playLive.member.usecase.LogIn
import com.quid.playLive.member.usecase.LogOut
import com.quid.playLive.member.usecase.SignUp
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
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

}