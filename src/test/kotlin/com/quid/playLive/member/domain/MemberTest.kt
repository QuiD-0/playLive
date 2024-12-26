package com.quid.playLive.member.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Member 도메인 테스트")
class MemberTest{

    @Test
    @DisplayName("회원 생성")
    fun makeUser(){
        val member = Member(
            email = "test@email.com",
            username = "test",
            password = "test",
            nickname = "test",
        )

        assertEquals("test", member.username)
    }

    @Test
    @DisplayName("회원 생성 실패")
    fun makeUserFail(){
        assertThrows(IllegalArgumentException::class.java){ Member(email = "", username = "test", password = "test", nickname = "test") }
        assertThrows(IllegalArgumentException::class.java){ Member(email = "test", username = "", password = "test", nickname = "test") }
        assertThrows(IllegalArgumentException::class.java){ Member(email = "test", username = "test", password = "", nickname = "test") }
        assertThrows(IllegalArgumentException::class.java){ Member(email = "test", username = "test", password = "test", nickname = "") }
    }

    @Test
    @DisplayName("비밀번호 암호화")
    fun encodePassword() {
        val member = Member(
            email = "test",
            username = "test",
            password = "test",
            nickname = "test",
        )

        val encodedPassword = "encodedPassword"
        val encodedMember = member.encodePassword(encodedPassword)
        assertEquals(encodedPassword, encodedMember.password)
    }
}
