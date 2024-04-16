package com.quid.playLive.user.domain

interface AuthType {
    val name: String

    companion object {
        fun of(authorityName: String): AuthType =
            RoleType.entries.find { it.name == authorityName }
                ?: throw IllegalArgumentException("권한이 존재하지 않습니다.")
    }
}

enum class RoleType : AuthType {
    ROLE_ADMIN,
    ROLE_USER
    ;
}