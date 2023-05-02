package com.pl.domain.usecase

import com.pl.domain.MemberState
import com.pl.domain.StatusBoardClient
import javax.inject.Inject

class SetMemberStateUseCase @Inject constructor(
    private val statusBoardClient: StatusBoardClient
) {
    suspend operator fun invoke(memberState: MemberState) = statusBoardClient.setMemberState(memberState)
}