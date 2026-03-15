package com.quid.playLive.chat.service

import com.quid.playLive.chat.domain.Chat
import com.quid.playLive.chat.infra.repository.ChatRepository
import com.quid.playLive.stage.service.StageInfoService
import org.springframework.stereotype.Service

@Service
class ChatInfoService(
    private val stageInfo: StageInfoService,
    private val chatRepository: ChatRepository
) {
    fun getRoomId(channel: String): String {
        return stageInfo.findChattingRoomId(channel)
    }

    fun getHistory(chatroomId: String, size: Int): List<Chat> {
        val clampedSize = size.coerceIn(1, 100)
        return chatRepository.findRecent(chatroomId, clampedSize)
    }
}
