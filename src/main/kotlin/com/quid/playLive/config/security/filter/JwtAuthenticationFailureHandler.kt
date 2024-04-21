package com.quid.playLive.config.security.filter

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component

@Component
class JwtAuthenticationFailureHandler : AccessDeniedHandler {
    override fun handle(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        accessDeniedException: AccessDeniedException?
    ) {
        sendError(response, accessDeniedException)
    }

    private fun sendError(
        response: HttpServletResponse?,
        accessDeniedException: AccessDeniedException?
    ) {
        response?.apply {
            status = 403
            contentType = "application/json"
            writer.write(errorResponse(accessDeniedException))
        }
    }

    private fun errorResponse(accessDeniedException: AccessDeniedException?): String =
        ObjectMapper().writeValueAsString(accessDeniedException?.message ?: "Access Denied")

}