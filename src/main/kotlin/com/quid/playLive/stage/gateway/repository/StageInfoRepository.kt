package com.quid.playLive.stage.gateway.repository

import com.quid.playLive.stage.domain.StageInfo
import com.quid.playLive.stage.gateway.api.model.MainStageResponse
import com.quid.playLive.stage.gateway.repository.jdbc.StageInfoJdbcClient
import com.quid.playLive.stage.gateway.repository.jpa.StageInfoEntity
import com.quid.playLive.stage.gateway.repository.jpa.StageInfoJpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

interface StageInfoRepository {
    fun save(stageInfo: StageInfo)
    fun findById(stageInfoSeq: Long): StageInfo
    fun findAll(channels: List<String>): List<MainStageResponse>
    fun findByMemberSeq(memberSeq: Long): StageInfoEntity

    @Repository
    class StageInfoRepositoryImpl(
        private val jpa: StageInfoJpaRepository,
        private val jdbcClient: StageInfoJdbcClient
    ) : StageInfoRepository {

        @Transactional
        override fun save(stageInfo: StageInfo) {
            jpa.save(StageInfoEntity(stageInfo))
        }

        @Transactional(readOnly = true)
        override fun findById(stageInfoSeq: Long): StageInfo =
            jpa.findByIdOrNull(stageInfoSeq)
                ?.toDomain()
                ?: throw IllegalArgumentException("Stage not found")

        @Transactional(readOnly = true)
        override fun findAll(channels: List<String>): List<MainStageResponse> =
            jdbcClient.findAll(listOf("quid"))

        @Transactional(readOnly = true)
        override fun findByMemberSeq(memberSeq: Long): StageInfoEntity =
            jpa.findByMemberSeq(memberSeq)
                ?: throw IllegalArgumentException("Stage not found")
    }
}