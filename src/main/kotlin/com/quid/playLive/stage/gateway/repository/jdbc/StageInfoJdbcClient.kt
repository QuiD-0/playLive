package com.quid.playLive.stage.gateway.repository.jdbc

import com.quid.playLive.stage.gateway.api.model.MainStageResponse
import org.springframework.jdbc.core.simple.JdbcClient
import org.springframework.stereotype.Component

@Component
class StageInfoJdbcClient(
    private val jdbc: JdbcClient
) {
    fun findAll(channels: List<String>): List<MainStageResponse> {
        if(channels.isEmpty()) return emptyList()

        val sql = """
            SELECT
                m.username,
                m.nickname,
                s.title,
                m.avatar
            FROM
                member m left join stage_info s on m.member_seq = s.member_seq
            WHERE
                m.username IN (:channels)
        """.trimIndent()

        return jdbc
            .sql(sql)
            .param("channels", channels)
            .query(MainStageResponse::class.java)
            .list()
    }
}