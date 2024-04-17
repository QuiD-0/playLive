package com.quid.playLive.user.gateway.repository.jpa

import com.quid.playLive.user.domain.User
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "USER", indexes = [Index(name = "IDX_USER_USERNAME", columnList = "USERNAME")])
class UserEntity(
    @Id
    @Column(name = "USER_SEQ")
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
    constructor(user: User) : this(
        id = user.id,
        email = user.email,
        username = user.username,
        password = user.password,
        nickname = user.nickname,
        streamKey = user.streamKey,
        regDate = user.regDate
    )

    fun toDomain() = User(
        id = id,
        email = email,
        username = username,
        password = password,
        nickname = nickname,
        streamKey = streamKey,
        regDate = regDate,
    )
}