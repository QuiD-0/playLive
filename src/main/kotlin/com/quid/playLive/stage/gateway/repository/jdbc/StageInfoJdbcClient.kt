package com.quid.playLive.stage.gateway.repository.jdbc

import com.quid.playLive.stage.gateway.api.model.MainStageResponse
import com.quid.playLive.stage.gateway.repository.jpa.StageInfoEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.jdbc.core.simple.JdbcClient
import org.springframework.stereotype.Component

@Component
class StageInfoJdbcClient(
    private val jdbc: JdbcClient
) {
    fun findAllLiveChannel(pageable: Pageable): Page<MainStageResponse> {
        val sql = """
            SELECT
                m.username,
                m.nickname,
                s.title,
                m.avatar
            FROM
                member m left join stage_info s on m.id = s.member_id
            WHERE
                m.is_live_on = 1
            ORDER BY
                s.created_at DESC
            LIMIT
                ${pageable.pageSize}
            OFFSET
                ${pageable.offset}
        """.trimIndent()

        val countSql = """
            SELECT
                count(*) as count
            FROM
                member m left join stage_info s on m.id = s.member_id
            WHERE
                s.is_live_on = 1
        """.trimIndent()

        val count = jdbc
            .sql(countSql)
            .query(Long::class.java)
            .single()

        return jdbc
            .sql(sql)
            .query(MainStageResponse::class.java)
            .list()
            .let { PageImpl(it, pageable, count) }
    }

    fun findByChannel(channel: String): StageInfoEntity? {
        val sql = """
            SELECT 
                s.id,
                s.member_id,
                s.title,
                s.description,
                s.stream_key,
                s.is_live_on,
                s.live_start_date_time
            FROM
                stage_info s
            JOIN 
                member m ON s.member_id = m.id
            WHERE
                m.username = ?
        """.trimIndent()

        return jdbc
            .sql(sql)
            .param(channel)
            .query(StageInfoEntity::class.java)
            .single()
    }
}
