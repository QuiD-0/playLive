package com.quid.playLive.global

import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Component
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource

@Component
class CorsConfig : CorsConfigurationSource {
    override fun getCorsConfiguration(request: HttpServletRequest): CorsConfiguration {
        return CorsConfiguration().apply {
            allowedOriginPatterns = listOf("*")
            allowedMethods = listOf("GET", "POST", "PUT", "DELETE", "PATCH")
            allowedHeaders = listOf("*")
            allowCredentials = true
        }
    }
}
