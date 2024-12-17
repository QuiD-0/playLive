package com.quid.playLive.member.domain

import java.time.LocalDateTime

data class MemberAuthority(
    val id: Long? = null,
    val memberId: Long,
    val authority: AuthType,
    val regDate: LocalDateTime = LocalDateTime.now(),
    val deleted: Boolean = false,
) {
    constructor(memberId: Long, authType: AuthType = AuthType.ROLE_USER) : this(
        null,
        memberId,
        authType
    )

    fun delete() = copy(deleted = true)
}
