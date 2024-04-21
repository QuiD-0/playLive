package com.quid.playLive.stage.gateway.repository

import com.quid.playLive.stage.domain.StageInfo
import com.quid.playLive.stage.gateway.repository.jpa.StageInfoEntity
import com.quid.playLive.stage.gateway.repository.jpa.StageInfoJpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

interface StageInfoRepository {
    fun save(stageInfo: StageInfo)
    fun findById(stageInfoSeq: Long): StageInfo

    @Repository
    class StageInfoRepositoryImpl(
        private val jpa: StageInfoJpaRepository
    ) : StageInfoRepository {

        @Transactional
        override fun save(stageInfo: StageInfo) {
            jpa.save(StageInfoEntity(stageInfo))
        }

        @Transactional(readOnly = true)
        override fun findById(stageInfoSeq: Long): StageInfo {
            return jpa.findByIdOrNull(stageInfoSeq)
                ?.toDomain()
                ?: throw IllegalArgumentException("Stage not found")
        }
    }
}