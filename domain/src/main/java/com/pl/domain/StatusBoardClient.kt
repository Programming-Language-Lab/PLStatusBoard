package com.pl.domain

interface StatusBoardClient {
    suspend fun postWebHookMessage(message: WebHookMessage)

    suspend fun getMemberState(name: String): MemberState
}
