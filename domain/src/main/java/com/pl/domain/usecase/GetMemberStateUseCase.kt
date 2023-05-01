package com.pl.domain.usecase

import com.pl.domain.StatusBoardClient
import javax.inject.Inject

class GetMemberStateUseCase @Inject constructor(
    private val statusBoardClient: StatusBoardClient
) {

    suspend operator fun invoke(name: String) = statusBoardClient.getMemberState(name)
}