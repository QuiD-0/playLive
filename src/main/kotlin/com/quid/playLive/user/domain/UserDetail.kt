package com.quid.playLive.user.domain

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails


data class UserDetail(
    val user: User,
    val authority: List<UserAuthority>,
) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> =
        authority.map { GrantedAuthority { it.authority.name } }.toMutableList()

    override fun getPassword(): String =
        user.password

    override fun getUsername(): String =
        user.username

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true

}