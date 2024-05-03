package com.quid.playLive.global.api

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter.ofPattern

sealed interface ApiResponse<RESPONSE> {
    val response: RESPONSE
    val status: String
    val timeStamp: String
        get() = LocalDateTime.now().format(ofPattern("yyyy-MM-dd HH:mm:ss"))
}

data class Success<RESPONSE>(
    override val response: RESPONSE
) : ApiResponse<RESPONSE> {
    override val status: String = "SUCCESS"
}

data class Error(
    override val response: String,
) : ApiResponse<String> {
    override val status: String = "ERROR"
}

fun <RESPONSE> Success(data: () -> RESPONSE): Success<RESPONSE> = Success(data())
fun Error(message: () -> String): Error = Error(message())