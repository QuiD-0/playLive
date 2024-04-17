package com.quid.playLive.member.gateway.repository.jpa

import com.quid.playLive.member.domain.Member
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "MEMBER", indexes = [Index(name = "IDX_MEMBER_USERNAME", columnList = "USERNAME")])
class MemberEntity(
    @Id
    @Column(name = "MEMBER_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "EMAIL")
    val email: String,

    @Column(name = "USERNAME")
    val username: String,

    @Column(name = "PASSWORD")
    val password: String,

    @Column(name = "NICKNAME")
    val nickname: String,

    @Column(name = "STREAM_KEY")
    val streamKey: String,

    @Column(name = "REG_DATE")
    val regDate: LocalDateTime,
) {
    constructor(member: Member) : this(
        id = member.id,
        email = member.email,
        username = member.username,
        password = member.password,
        nickname = member.nickname,
        streamKey = member.streamKey,
        regDate = member.regDate
    )

    fun toDomain() = Member(
        id = id,
        email = email,
        username = username,
        password = password,
        nickname = nickname,
        streamKey = streamKey,
        regDate = regDate,
    )
}