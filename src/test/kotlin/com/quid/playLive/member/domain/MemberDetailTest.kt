package com.quid.playLive.member.domain

import com.quid.playLive.fixture.MemberFixture
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.security.core.GrantedAuthority

@DisplayName("MemberDetail 테스트")
class MemberDetailTest {

    private val memberDetail = MemberFixture.memberDetail

    @Test
    @DisplayName("권한 가져오기")
    fun testGetAuthorities() {
        val authorities = memberDetail.authority
        val grantedAuthorities = memberDetail.authority.map { GrantedAuthority { it.authority.name } }.toMutableList()
        assert(authorities.size == grantedAuthorities.size)
        assert(authorities[0].authority.name == grantedAuthorities[0].authority)
    }

    @Test
    @DisplayName("비밀번호 가져오기")
    fun testGetPassword() {
        assert(memberDetail.getPassword() == MemberFixture.member.password)
    }

    @Test
    @DisplayName("유저네임 가져오기")
    fun testGetUsername() {
        assert(memberDetail.getUsername() == MemberFixture.member.username)
    }

    @Test
    @DisplayName("계정 만료 여부")
    fun testIsAccountNonExpired() {
        assert(memberDetail.isAccountNonExpired())
    }

    @Test
    @DisplayName("계정 잠금 여부")
    fun testIsAccountNonLocked() {
        assert(memberDetail.isAccountNonLocked())
    }

    @Test
    @DisplayName("자격 증명 만료 여부")
    fun testIsCredentialsNonExpired() {
        assert(memberDetail.isCredentialsNonExpired())
    }

    @Test
    @DisplayName("활성화 여부")
    fun testIsEnabled() {
        assert(memberDetail.isEnabled())
    }

    @Test
    @DisplayName("아이디 가져오기")
    fun testId() {
        assert(memberDetail.id == MemberFixture.member.id)
    }

}