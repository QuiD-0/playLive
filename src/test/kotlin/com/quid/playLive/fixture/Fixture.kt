package com.quid.playLive.fixture

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.quid.playLive.global.api.ErrorHandling
import org.mockito.ArgumentMatchers.any
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.filter.CharacterEncodingFilter


fun <T> any() : T = any()

fun <T> mvc(controller: T): MockMvc = MockMvcBuilders.standaloneSetup(controller)
    .setMessageConverters(MappingJackson2HttpMessageConverter())
    .setControllerAdvice(ErrorHandling())
    .addFilter<StandaloneMockMvcBuilder>(CharacterEncodingFilter("UTF-8", true))
    .alwaysDo<StandaloneMockMvcBuilder>(print())
    .build()

fun securityMvc(context: WebApplicationContext): MockMvc = MockMvcBuilders
    .webAppContextSetup(context)
    .addFilter<DefaultMockMvcBuilder>(CharacterEncodingFilter("UTF-8", true))
    .alwaysDo<DefaultMockMvcBuilder>(print())
    .build()
    .also {
        SecurityContextHolder.getContext().authentication =
            UsernamePasswordAuthenticationToken(
                MemberFixture.memberDetail,
                null,
                MemberFixture.memberDetail.authorities
            )
    }

fun mapper(): ObjectMapper = ObjectMapper().registerModule(JavaTimeModule())
