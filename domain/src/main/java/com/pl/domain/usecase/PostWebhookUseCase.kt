package com.pl.domain.usecase

import com.pl.domain.StatusBoardClient
import com.pl.domain.WebHookMessage
import javax.inject.Inject

class PostWebhookUseCase @Inject constructor(
    private val statusBoardClient: StatusBoardClient
) {

    suspend operator fun invoke(message: WebHookMessage) {
        statusBoardClient.postWebHookMessage(message)
    }
}