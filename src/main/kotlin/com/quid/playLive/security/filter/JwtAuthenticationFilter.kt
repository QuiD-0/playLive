package com.quid.playLive.security.filter

import com.quid.playLive.global.api.UnauthorizedException
import com.quid.playLive.member.usecase.UserAuthService
import com.quid.playLive.token.usecase.TokenDecoder
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

class JwtAuthenticationFilter(
    private val tokenDecoder: TokenDecoder,
    private val userAuthService: UserAuthService
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            val accessToken = getToken(request)
                .run { tokenDecoder(this) }
            require(accessToken.isNotExpired()) { throw UnauthorizedException("access token is expired") }

            val user = userAuthService.loadUserByUsername(accessToken.username)

            UsernamePasswordAuthenticationToken(user, null, user.authorities)
                .also { SecurityContextHolder.getContext().authentication = it }

        } catch (e: Exception) {
            request.setAttribute("Exception", e)
            SecurityContextHolder.clearContext()
        }
        filterChain.doFilter(request, response)
    }

    private fun getToken(request: HttpServletRequest): String {
        requireNotNull(request.getHeader("Authorization")) { throw UnauthorizedException("Authorization header is missing") }
        val header = request.getHeader("Authorization")
        require(header.startsWith("Bearer ")) { throw UnauthorizedException("Authorization header must start with Bearer") }
        return header.substring(7)
    }

}
