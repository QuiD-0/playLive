package com.quid.playLive.stage.gateway.repository.jpa

import org.springframework.data.jpa.repository.JpaRepository

interface StageHistoryJpaRepository : JpaRepository<OnAirHistoryEntity, Long> {
    fun save(toHistoryEntity: OnAirHistoryEntity)
}
