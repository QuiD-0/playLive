package com.quid.playLive.member.domain

import java.time.LocalDateTime

data class MemberAuthority(
    val id: Long? = null,
    val memberSeq: Long,
    val authority: AuthType,
    val regDate: LocalDateTime = LocalDateTime.now(),
    val deleted: Boolean = false,
) {
    constructor(memberSeq: Long, authorityName: String) : this(
        null,
        memberSeq,
        AuthType.valueOf(authorityName),
    )

    fun delete() = copy(deleted = true)
}

fun default(memberSeq: Long) = MemberAuthority(
    memberSeq = memberSeq,
    authority = AuthType.ROLE_USER,
)