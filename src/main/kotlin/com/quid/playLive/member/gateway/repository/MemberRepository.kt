package com.quid.playLive.member.gateway.repository

import com.quid.playLive.member.domain.Member
import com.quid.playLive.member.gateway.repository.jpa.JpaMemberRepository
import com.quid.playLive.member.gateway.repository.jpa.MemberEntity
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

interface MemberRepository {
    fun findById(id: Long): Member
    fun findByChannel(channel: String): Member
    fun save(member: Member): Member
    fun findByUsername(username: String): Member
    fun existsByUsername(username: String): Boolean
    fun findByStreamKey(streamKey: String): Member

    @Repository
    class MemberRepositoryImpl(
        private val jpaMemberRepository: JpaMemberRepository
    ) : MemberRepository {

        @Transactional(readOnly = true)
        override fun findById(id: Long): Member =
            jpaMemberRepository.findByIdOrNull(id)
                ?.toDomain()
                ?: throw IllegalArgumentException("User not found")

        @Transactional(readOnly = true)
        override fun findByChannel(channel: String): Member =
            jpaMemberRepository.findByUsername(channel)
                ?.toDomain()
                ?: throw IllegalArgumentException("User not found")

        @Transactional
        override fun save(member: Member) = jpaMemberRepository.save(MemberEntity(member)).toDomain()

        @Transactional(readOnly = true)
        override fun findByUsername(username: String): Member =
            jpaMemberRepository.findByUsername(username)?.toDomain()
                ?: throw IllegalArgumentException("User not found")

        override fun existsByUsername(username: String): Boolean =
            jpaMemberRepository.existsByUsername(username)

        override fun findByStreamKey(streamKey: String): Member =
            jpaMemberRepository.findByStreamKey(streamKey)
                ?.toDomain()
                ?: throw IllegalArgumentException("User not found")

    }
}