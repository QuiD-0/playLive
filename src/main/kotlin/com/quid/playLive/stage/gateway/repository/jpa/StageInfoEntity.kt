package com.quid.playLive.stage.gateway.repository.jpa

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(
    name = "stage_info", indexes = [
        Index(name = "IDX_STAGE_INFO_MEMBER_ID", columnList = "MEMBER_ID"),
    ]
)
class StageInfoEntity(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    @Column(name = "member_id")
    val memberId: Long,
    @Column(name = "title")
    val title: String,
    @Column(name = "description")
    val description: String,
    @Column(name = "stream_key")
    val streamKey: String,
    @Column(name = "is_live_on")
    val isLiveOn: Boolean,
    @Column(name = "live_start_date_time")
    val liveStartDateTime: LocalDateTime?,
)
