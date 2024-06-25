package com.example.springbootdemo.security

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatcher
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatcher.MatchResult.match
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatcher.MatchResult.notMatch
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange

@Component
class AuthHeaderWebExchangeMatcher(
    @Value("\${security.token.validation.requireAuthHeaders:false}") private val authHeaderRequired: Boolean
) : ServerWebExchangeMatcher {
    override fun matches(exchange: ServerWebExchange) =
        if (tokenIsMissingButNotRequired(exchange)) match() else notMatch()

    private fun tokenIsMissingButNotRequired(exchange: ServerWebExchange): Boolean {
        val authHeader: String? = exchange.request.headers.getFirst(HttpHeaders.AUTHORIZATION)
        return !authHeaderRequired && (
                authHeader.isNullOrEmpty() || !isValidToken(authHeader)
                )
    }

    private fun isValidToken(authHeader: String): Boolean {
        return authHeader.contains("ProprietaryToken=")
    }
}
