package com.quid.playLive.fixture

import com.quid.playLive.member.domain.AuthType
import com.quid.playLive.member.domain.Member
import com.quid.playLive.member.domain.MemberAuthority
import com.quid.playLive.member.domain.MemberDetail

class MemberFixture {

    companion object {
        val member = Member(
            id = 1L,
            username = "test",
            password = "test",
            email = "test@mail.com",
            nickname = "test"
        )

        val authority = MemberAuthority(
            id = 1L,
            memberId = 1L,
            authority = AuthType.ROLE_USER
        )

        val memberDetail = MemberDetail(
            member = member,
            authority = listOf(authority)
        )
    }
}
