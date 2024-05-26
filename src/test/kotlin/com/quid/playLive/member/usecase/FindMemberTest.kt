package com.quid.playLive.member.usecase

import com.quid.playLive.fixture.any
import com.quid.playLive.member.gateway.repository.MemberRepository
import com.quid.playLive.member.usecase.FindMember.FindMemberUseCase
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito.mock

class FindMemberTest {

    private val repository = mock<MemberRepository>()

    private val findMember = FindMemberUseCase(repository)

    @Test
    @DisplayName("회원 존재 여부")
    fun existUser() {
        given(repository.existsByUsername(any())).willReturn(true)

        val exists = findMember.exists("user")
        assertTrue(exists)
    }

    @Test
    @DisplayName("회원 미존재 여부")
    fun notExistUser() {
        given(repository.existsByUsername(any())).willReturn(false)

        val exists = findMember.exists("user")
        assertFalse(exists)
    }
}