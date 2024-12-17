package com.quid.playLive.member.gateway.repository.jpa

import com.quid.playLive.member.domain.AuthType
import com.quid.playLive.member.domain.MemberAuthority
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType.IDENTITY
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "member_authority")
class MemberAuthorityEntity(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = IDENTITY)
    val id: Long?,
    @Column(name = "member_id")
    val memberId: Long,
    @Column(name = "authority")
    val authority: String,
    @Column(name = "reg_date")
    val regDate: LocalDateTime,
    @Column(name = "deleted")
    val deleted: Boolean,
) {
    constructor(authority: MemberAuthority) : this(
        authority.id,
        authority.memberId,
        authority.authority.name,
        authority.regDate,
        authority.deleted,
    )

    fun toDomain() = MemberAuthority(
        id,
        memberId,
        AuthType.valueOf(authority),
        regDate,
        deleted,
    )
}
