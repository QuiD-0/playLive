package com.quid.playLive.fixture

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.quid.playLive.global.api.ErrorHandling
import org.mockito.Mockito
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder
import org.springframework.web.context.WebApplicationContext


fun <T> any() = Mockito.any() ?: null as T

fun <T> mvc(controller: T): MockMvc = MockMvcBuilders.standaloneSetup(controller)
    .setMessageConverters(MappingJackson2HttpMessageConverter())
    .setControllerAdvice(ErrorHandling())
    .alwaysDo<StandaloneMockMvcBuilder>(print())
    .build()

fun securityMvc(context: WebApplicationContext): MockMvc = MockMvcBuilders
    .webAppContextSetup(context)
    .alwaysDo<DefaultMockMvcBuilder>(print())
    .build()
    .also {
        SecurityContextHolder.getContext().authentication =
            UsernamePasswordAuthenticationToken(MemberFixture.memberDetail, null)
    }

fun mapper(): ObjectMapper = ObjectMapper().registerModule(JavaTimeModule())
