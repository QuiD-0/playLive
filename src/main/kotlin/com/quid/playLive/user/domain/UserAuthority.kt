package com.quid.playLive.user.domain

import java.time.LocalDateTime

data class UserAuthority(
    val id: Long? = null,
    val userSeq: Long,
    val authority: AuthType,
    val regDate: LocalDateTime = LocalDateTime.now(),
    val deleted: Boolean = false,
) {
    constructor(userSeq: Long, authorityName: AuthType) : this(
        null,
        userSeq,
        authorityName,
    )

    fun delete() = copy(deleted = true)
}