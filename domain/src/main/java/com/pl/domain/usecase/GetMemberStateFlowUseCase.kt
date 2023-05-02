package com.pl.domain.usecase

import com.pl.domain.StatusBoardClient
import javax.inject.Inject

class GetMemberStateFlowUseCase @Inject constructor(
    private val statusBoardClient: StatusBoardClient
) {
    operator fun invoke(name: String) = statusBoardClient.getMemberStateFlow(name)
}