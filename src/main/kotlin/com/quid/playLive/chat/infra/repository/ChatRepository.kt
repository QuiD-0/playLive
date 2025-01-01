package com.quid.playLive.chat.infra.repository

import com.quid.playLive.chat.domain.Chat
import com.quid.playLive.chat.infra.ChatMapper
import org.springframework.stereotype.Repository

@Repository
class ChatRepository(
    private val jpa: ChatJpaRepository
) {
    fun save(chat: Chat) {
        jpa.save(ChatMapper.toEntity(chat))
    }

    fun findLast50(chatroomId: String): List<Chat> {
        return jpa.findLast50ByChatroomIdOrderByRegDate(chatroomId)
            .map { ChatMapper.toDomain(it) }
    }
}
