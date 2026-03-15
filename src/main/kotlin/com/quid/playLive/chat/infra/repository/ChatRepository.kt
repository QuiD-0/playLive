package com.quid.playLive.chat.infra.repository

import com.quid.playLive.chat.domain.Chat
import com.quid.playLive.chat.infra.ChatMapper
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Repository

@Repository
class ChatRepository(
    private val jpa: ChatJpaRepository
) {
    fun save(chat: Chat) {
        jpa.save(ChatMapper.toEntity(chat))
    }

    fun findRecent(chatroomId: String, size: Int = 50): List<Chat> {
        return jpa.findByChatroomIdOrderByRegDateDesc(chatroomId, PageRequest.of(0, size))
            .reversed()
            .map { ChatMapper.toDomain(it) }
    }
}
