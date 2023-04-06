package com.pl.domain

interface StatusBoardClient {
    suspend fun postWebHookMessage(message: WebHookMessage)
}
