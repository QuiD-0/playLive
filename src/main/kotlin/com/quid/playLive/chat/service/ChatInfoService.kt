package com.quid.playLive.chat.service

import com.quid.playLive.stage.service.StageInfoService
import org.springframework.stereotype.Service

@Service
class ChatInfoService(
    private val stageInfo: StageInfoService
) {
    fun getRoomId(channel: String): String {
        return stageInfo.findChattingRoomId(channel)
    }
}
