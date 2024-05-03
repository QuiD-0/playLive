package com.quid.playLive.stage.gateway.repository.jpa

import com.quid.playLive.stage.domain.StageInfo
import jakarta.persistence.*

@Entity
@Table(name = "stage_info", indexes = [
    Index(name = "IDX_STAGE_INFO_MEMBER_SEQ", columnList = "MEMBER_SEQ"),
])
class StageInfoEntity(
    @Id
    @Column(name = "stage_info_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    @Column(name = "member_seq")
    val memberSeq: Long,
    @Column(name = "title")
    val title: String,
    @Column(name = "description")
    val description: String,
) {
    constructor(stageInfo: StageInfo) : this(
        stageInfo.id,
        stageInfo.memberSeq,
        stageInfo.title,
        stageInfo.description,
    )

    fun toDomain() = StageInfo(
        id,
        memberSeq,
        title,
        description,
    )
}