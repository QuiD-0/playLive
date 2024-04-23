package com.quid.playLive.member.domain

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails


data class MemberDetail(
    val member: Member,
    val authority: List<MemberAuthority>,
) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> =
        authority.map { GrantedAuthority { it.authority.name } }.toMutableList()

    override fun getPassword(): String =
        member.password

    override fun getUsername(): String =
        member.username

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
    
    val id: Long = member.id!!

}