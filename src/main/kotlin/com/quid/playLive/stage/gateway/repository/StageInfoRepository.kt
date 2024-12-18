package com.quid.playLive.stage.gateway.repository

import com.quid.playLive.stage.domain.StageInfo
import com.quid.playLive.stage.gateway.api.model.MainStageResponse
import com.quid.playLive.stage.gateway.repository.jdbc.StageInfoJdbcClient
import com.quid.playLive.stage.gateway.repository.jpa.StageInfoJpaRepository
import com.quid.playLive.stage.gateway.repository.jpa.toDomain
import com.quid.playLive.stage.gateway.repository.jpa.toEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class StageInfoRepository(
    private val jpa: StageInfoJpaRepository,
    private val jdbcClient: StageInfoJdbcClient
) {

    @Transactional
    fun save(stageInfo: StageInfo) {
        jpa.save(toEntity(stageInfo))
    }

    @Transactional(readOnly = true)
    fun findByChannel(channel: String): StageInfo {
        return jdbcClient.findByChannel(channel)?.let { toDomain(it) }
            ?: throw IllegalArgumentException("Stage not found")
    }

    @Transactional(readOnly = true)
    fun findById(stageInfoId: Long): StageInfo =
        jpa.findByIdOrNull(stageInfoId)?.let { toDomain(it) } ?: throw IllegalArgumentException("Stage not found")

    @Transactional(readOnly = true)
    fun findByMemberId(memberId: Long): StageInfo =
        jpa.findByMemberId(memberId)?.let { toDomain(it) } ?: throw IllegalArgumentException("Stage not found")

    @Transactional(readOnly = true)
    fun findAllLiveChannel(pageable: Pageable): Page<MainStageResponse> {
        return jdbcClient.findAllLiveChannel(pageable)
    }
}
