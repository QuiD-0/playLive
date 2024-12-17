package com.quid.playLive.member.gateway.repository.jpa

import com.quid.playLive.member.domain.Member
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "member", indexes = [Index(name = "IDX_MEMBER_USERNAME", columnList = "username")])
class MemberEntity(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "email")
    val email: String,

    @Column(name = "username")
    val username: String,

    @Column(name = "password")
    val password: String,

    @Column(name = "nickname")
    val nickname: String,

    @Column(name = "avatar")
    val avatar: String,

    @Column(name = "stream_key")
    val streamKey: String,

    @Column(name = "reg_date")
    val regDate: LocalDateTime,
) {
    constructor(member: Member) : this(
        id = member.id,
        email = member.email,
        username = member.username,
        password = member.password,
        nickname = member.nickname,
        avatar = member.avatar,
        streamKey = member.streamKey,
        regDate = member.regDate
    )

    fun toDomain() = Member(
        id = id,
        email = email,
        username = username,
        password = password,
        nickname = nickname,
        avatar = avatar,
        streamKey = streamKey,
        regDate = regDate,
    )
}
