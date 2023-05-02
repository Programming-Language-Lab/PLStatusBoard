package com.pl.data

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.snapshots
import com.google.firebase.ktx.Firebase
import com.pl.data.model.firebase.FireStoreDocumentResponse
import com.pl.data.model.firebase.FireStoreMembersFields
import com.pl.data.model.firebase.MemberStateResponse
import com.pl.domain.MemberInfo
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
import kotlinx.coroutines.flow.map


class StatusBoardClientImpl : StatusBoardClient {

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

    override fun getMemberStateFlow(name: String) =
        Firebase.firestore.collection(DATA_BASE_NAME)
            .document(name)
            .snapshots()
            .map {
                it.toObject(MemberStateResponse::class.java)?.toMemberState()
                    ?.copy(MemberInfo.findKoreanByEnglish(name)) ?: MemberState.error()
            }

    override suspend fun setMemberState(memberState: MemberState) {
        Firebase.firestore.collection(DATA_BASE_NAME)
            .document(MemberInfo.findEnglishByKorean(memberState.name))
            .set(MemberStateResponse(memberState.name, memberState.status.text))
    }

    companion object {
        private const val FIRE_STORE_END_POINT = "https://firestore.googleapis.com/v1"
        private const val PROJECT_ID = "plstatusboard"
        private const val DATA_BASE_NAME = "Members"
        private const val FIRE_STORE_BASE_URL =
            "$FIRE_STORE_END_POINT/projects/$PROJECT_ID/databases/(default)/documents/$DATA_BASE_NAME"
    }
}
