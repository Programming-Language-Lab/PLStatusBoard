package com.pl.domain.usecase

import com.pl.domain.MemberState
import javax.inject.Inject

class CheckDuplicatedMemberStateUseCase @Inject constructor(
    private val getMemberStateFromCacheUseCase: GetMemberStateFromCacheUseCase
) {
    suspend operator fun invoke(memberState: MemberState): Boolean {

        val memberStateFromCache = getMemberStateFromCacheUseCase(memberState.name)
        return memberStateFromCache == memberState.status.text
    }
}