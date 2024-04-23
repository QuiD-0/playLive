package com.quid.playLive.stage.gateway.api.model

import com.quid.playLive.member.domain.MemberDetail

data class StageInfoUpdateRequest(
    val channel: String,
    val title: String,
    val description: String,
) {
    fun isSameChannel(member: MemberDetail): Boolean {
        return member.username == channel
    }
}
