package com.quid.playLive.member.domain

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MemberTest{

    @Test
    fun makeUser(){
        val member = Member(
            email = "test@email.com",
            username = "test",
            password = "test",
            nickname = "test",
        )

        assertEquals("test", member.username)
        assertNotNull(member.streamKey)
    }
}