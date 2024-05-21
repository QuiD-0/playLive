package com.quid.playLive.member.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("MemberAuthority 테스트")
class MemberAuthorityTest {

    @Test
    @DisplayName("멤버 권한 생성")
    fun makeMemberAuthority() {
        val memberAuthority = MemberAuthority(
            memberSeq = 1,
            authority = AuthType.ROLE_USER,
        )

        assertEquals(1, memberAuthority.memberSeq)
        assertEquals(AuthType.ROLE_USER, memberAuthority.authority)
    }

    @Test
    @DisplayName("멤버 권한 기본값 생성")
    fun makeMemberAuthorityWithDefault() {
        val memberAuthority = MemberAuthority(
            memberSeq = 1
        )

        assertEquals(1, memberAuthority.memberSeq)
        assertEquals(AuthType.ROLE_USER, memberAuthority.authority)
    }

    @Test
    @DisplayName("멤버 권한 삭제")
    fun deleteMemberAuthority() {
        val memberAuthority = MemberAuthority(
            memberSeq = 1,
            authority = AuthType.ROLE_USER,
        )

        val deletedMemberAuthority = memberAuthority.delete()
        assertEquals(true, deletedMemberAuthority.deleted)
    }


}