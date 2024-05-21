package com.quid.playLive.member.domain

import java.time.LocalDateTime

data class MemberAuthority(
    val id: Long? = null,
    val memberSeq: Long,
    val authority: AuthType,
    val regDate: LocalDateTime = LocalDateTime.now(),
    val deleted: Boolean = false,
) {
    constructor(memberSeq: Long, authType: AuthType = AuthType.ROLE_USER) : this(
        null,
        memberSeq,
        authType
    )

    fun delete() = copy(deleted = true)
}