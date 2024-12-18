package com.quid.playLive.stage.gateway.repository

import com.quid.playLive.stage.domain.StageInfo
import com.quid.playLive.stage.gateway.repository.jpa.StageHistoryJpaRepository
import com.quid.playLive.stage.gateway.repository.jpa.toHistoryEntity
import org.springframework.stereotype.Repository

@Repository
class StageHistoryRepository(
    private val jpa: StageHistoryJpaRepository
) {
    fun save(stageInfo: StageInfo) {
        val toHistoryEntity = toHistoryEntity(stageInfo)
        jpa.save(toHistoryEntity)
    }
}
