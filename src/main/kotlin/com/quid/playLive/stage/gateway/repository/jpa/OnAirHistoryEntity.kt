package com.quid.playLive.stage.gateway.repository.jpa

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime


@Entity
@Table(
    name = "on_air_history"
)
class OnAirHistoryEntity(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column(name = "member_id")
    val memberId: Long,
    @Column(name = "title")
    val title: String,
    @Column(name = "start_date_time")
    val startDateTime: LocalDateTime,
    @Column(name = "end_date_time")
    val endDateTime: LocalDateTime,
) {

}
