package com.pl.data

import com.pl.data.model.firebase.FireStoreDocumentResponse
import com.pl.data.model.firebase.FireStoreMembersFields
import com.pl.domain.MemberState
import com.pl.domain.StatusBoardClient
import com.pl.domain.WebHookMessage
import io.ktor.client.*
import io.ktor.client.call.body
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

    override suspend fun getMemberState(name: String): MemberState {
        val result = client.get("$FIRE_STORE_BASE_URL/$name") {
            header(HttpHeaders.ContentType, ContentType.Application.Json)
        }.body<FireStoreDocumentResponse<FireStoreMembersFields>>()
        return result.toMemberState()
    }

    companion object {
        private const val FIRE_STORE_END_POINT = "https://firestore.googleapis.com/v1"
        private const val PROJECT_ID = "plstatusboard"
        private const val DATA_BASE_NAME = "Members"
        private const val FIRE_STORE_BASE_URL = "$FIRE_STORE_END_POINT/projects/$PROJECT_ID/databases/(default)/documents/$DATA_BASE_NAME"
    }
}
