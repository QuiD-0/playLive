package com.quid.playLive.user.gateway.repository

import com.quid.playLive.user.domain.User
import com.quid.playLive.user.gateway.repository.jpa.JpaUserRepository
import com.quid.playLive.user.gateway.repository.jpa.UserEntity
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

interface UserRepository {
    fun findById(id: Long): User
    fun findByChannel(channel: String): User
    fun save(user: User): User

    @Repository
    class UserRepositoryImpl(
        private val jpaUserRepository: JpaUserRepository
    ) : UserRepository {
        override fun findById(id: Long): User =
            jpaUserRepository.findByIdOrNull(id)
                ?.toDomain()
                ?: throw IllegalArgumentException("User not found")

        override fun findByChannel(channel: String): User =
            jpaUserRepository.findByUsername(channel)
                ?.toDomain()
                ?: throw IllegalArgumentException("User not found")

        override fun save(user: User) = jpaUserRepository.save(UserEntity(user)).toDomain()

    }
}