package com.pl.data

import com.pl.domain.StatusBoardClient
import com.pl.domain.WebHookMessage
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*

class StatusBoardClientImpl: StatusBoardClient {

    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json()
        }
    }

    override suspend fun postWebHookMessage(message: WebHookMessage) {
        client
            .post(BuildConfig.webhookUrl) {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
                setBody(message)
            }

    }
}
