package com.pl.domain

import kotlinx.coroutines.flow.Flow

interface StatusBoardClient {
    suspend fun postWebHookMessage(message: WebHookMessage)

    suspend fun getMemberState(name: String): MemberState

    fun getMemberStateFlow(name: String): Flow<MemberState>

    suspend fun setMemberState(memberState: MemberState)
}
