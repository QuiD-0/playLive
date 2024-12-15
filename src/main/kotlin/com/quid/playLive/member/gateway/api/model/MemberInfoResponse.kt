package com.quid.playLive.member.gateway.api.model

import com.quid.playLive.member.domain.Member

data class MemberInfoResponse(
    val id: Long? = null,
    val email: String,
    val nickname: String,
    val avatar: String,
) {
    constructor(member: Member) : this(
        id = member.id,
        email = member.email,
        nickname = member.nickname,
        avatar = member.avatar
    )
}
