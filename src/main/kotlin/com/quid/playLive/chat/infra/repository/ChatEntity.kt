package com.quid.playLive.chat.infra.repository

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "chat")
class ChatEntity(
    @Id
    @Column(name = "id")
    val id: String,
    @Column(name = "chatroom_id")
    val chatroomId: String,
    @Column(name = "sender_id")
    val senderId: Long,
    @Column(name = "type")
    val chatType: String,
    @Column(name = "message")
    val message: String,
    @Column(name = "nickname")
    val nickname: String,
    @Column(name = "reg_date")
    val regDate: LocalDateTime,
) {
}
