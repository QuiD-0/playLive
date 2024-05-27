package com.quid.playLive.member.gateway.repository

import com.quid.playLive.fixture.MemberFixture
import com.quid.playLive.member.gateway.repository.jpa.JpaMemberRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestConstructor
import org.springframework.test.context.TestConstructor.AutowireMode.ALL

@DataJpaTest
@ActiveProfiles("test")
@TestConstructor(autowireMode = ALL)
class MemberRepositoryTest(
    jpa: JpaMemberRepository
) {
    private val repository = MemberRepository.MemberRepositoryImpl(jpa)

    @Test
    fun findById() {
        repository.save(MemberFixture.member)

        val found = repository.findById(MemberFixture.member.id!!)
        assertEquals(MemberFixture.member, found)
    }

    @Test
    fun findByChannel() {
        repository.save(MemberFixture.member.copy(username = "channel"))

        val found = repository.findByChannel("channel")
        assertEquals("channel", found.username)
    }

    @Test
    fun save() {
        val member = MemberFixture.member.copy(id = null)
        val saved = repository.save(member)
        assertEquals(member.copy(id = 2), saved)
    }

    @Test
    fun findByUsername() {
        repository.save(MemberFixture.member.copy(username = "username"))

        val found = repository.findByUsername("username")
        assertEquals("username", found.username)
    }

    @Test
    fun existsByUsername() {
        repository.save(MemberFixture.member)

        val exists = repository.existsByUsername(MemberFixture.member.username)
        assertEquals(true, exists)
    }

    @Test
    fun findByStreamKey() {
        repository.save(MemberFixture.member.copy(streamKey = "streamKey"))

        val found = repository.findByStreamKey("streamKey")
        assertEquals("streamKey", found.streamKey)
    }

    @Test
    fun findByStreamKey_notFound() {
        assertThrows<IllegalArgumentException> { repository.findByStreamKey("notFound") }
    }

    @Test
    fun findByChannel_notFound() {
        assertThrows<IllegalArgumentException> { repository.findByChannel("notFound") }
    }

}