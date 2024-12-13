package com.quid.playLive.global.security

import com.quid.playLive.global.security.filter.FilterAuthenticationEntryPoint
import com.quid.playLive.global.security.filter.JwtAuthenticationFailureHandler
import com.quid.playLive.global.security.filter.JwtAuthenticationFilter
import com.quid.playLive.member.usecase.UserAuthService
import com.quid.playLive.token.usecase.TokenDecoder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.security.web.util.matcher.RequestMatcher

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val filterAuthenticationEntryPoint: FilterAuthenticationEntryPoint,
    private val jwtAuthenticationFailureHandler: JwtAuthenticationFailureHandler,
    private val userAuthService: UserAuthService,
    private val jwtTokenDecoder: TokenDecoder,
) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain =
        http.csrf { it.disable() }
            .formLogin { it.disable() }
            .httpBasic { it.disable() }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .addFilterBefore(
                JwtAuthenticationFilter(
                    jwtTokenDecoder,
                    userAuthService
                ),
                UsernamePasswordAuthenticationFilter::class.java
            )
            .exceptionHandling {
                it.authenticationEntryPoint(filterAuthenticationEntryPoint)
                it.accessDeniedHandler(jwtAuthenticationFailureHandler)
            }
            .authorizeHttpRequests {
                it.requestMatchers(*requiredApi()).authenticated()
                it.anyRequest().permitAll()
            }
            .build()

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    private fun requiredApi(): Array<RequestMatcher> = arrayOf(
        AntPathRequestMatcher("/api/member/me", "GET"),
        AntPathRequestMatcher("/api/member/logout", "POST")
    )

}
