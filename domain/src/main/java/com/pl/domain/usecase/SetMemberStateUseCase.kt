package com.pl.domain.usecase

import com.pl.domain.MemberState
import com.pl.domain.StatusBoardClient
import javax.inject.Inject

class SetMemberStateUseCase @Inject constructor(
    private val statusBoardClient: StatusBoardClient,
    private val getMemberStateFromCacheUseCase: GetMemberStateFromCacheUseCase,
    private val setMemberStateToCacheUseCase: SetMemberStateToCacheUseCase
) {
    suspend operator fun invoke(memberState: MemberState): Boolean {
        val cachedMemberState = getMemberStateFromCacheUseCase(memberState.name)
        return if (cachedMemberState != memberState.status.text) {
            setMemberStateToCacheUseCase(memberState)
            statusBoardClient.setMemberState(memberState)
            true
        } else {
            false
        }
    }
}