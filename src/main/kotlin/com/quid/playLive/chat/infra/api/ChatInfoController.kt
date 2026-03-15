package com.quid.playLive.chat.infra.api

import com.quid.playLive.chat.service.ChatInfoService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/chat")
class ChatInfoController(
    private val chat: ChatInfoService
) {

    @GetMapping("/roomId/{channel}")
    fun getRoomId(@PathVariable channel: String): String {
        return chat.getRoomId(channel)
    }

    @GetMapping("/history/{chatroomId}")
    fun getHistory(
        @PathVariable chatroomId: String,
        @RequestParam(defaultValue = "50") size: Int
    ): List<ChatHistoryResponse> {
        return chat.getHistory(chatroomId, size).map {
            ChatHistoryResponse(
                id = it.id.id,
                nickname = it.nickname,
                message = it.message,
                regDate = it.regDate.toString()
            )
        }
    }
}

data class ChatHistoryResponse(
    val id: String,
    val nickname: String,
    val message: String,
    val regDate: String
)
