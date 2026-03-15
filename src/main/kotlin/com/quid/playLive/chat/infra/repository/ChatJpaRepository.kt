package com.quid.playLive.chat.infra.repository

import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ChatJpaRepository : JpaRepository<ChatEntity, String> {

    @Query("SELECT c FROM ChatEntity c WHERE c.chatroomId = :chatroomId AND c.chatType = 'CHAT' ORDER BY c.regDate DESC")
    fun findByChatroomIdOrderByRegDateDesc(chatroomId: String, pageable: Pageable): List<ChatEntity>
}
