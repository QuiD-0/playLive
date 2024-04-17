package com.quid.playLive.user.domain

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class UserTest{

    @Test
    fun makeUser(){
        val user = User(
            email = "test@email.com",
            username = "test",
            password = "test",
            nickname = "test",
        )

        assertEquals("test", user.username)
        assertNotNull(user.streamKey)
    }
}