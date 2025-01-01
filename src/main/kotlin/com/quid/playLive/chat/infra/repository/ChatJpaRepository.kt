package com.quid.playLive.chat.infra.repository

import org.springframework.data.jpa.repository.JpaRepository

interface ChatJpaRepository: JpaRepository<ChatEntity, String> {
    fun findLast50ByChatroomIdOrderByRegDate(chatroomId: String): MutableList<ChatEntity>
}
