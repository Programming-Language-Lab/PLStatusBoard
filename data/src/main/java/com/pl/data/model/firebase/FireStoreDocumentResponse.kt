package com.pl.data.model.firebase

import com.pl.domain.MemberState
import com.pl.domain.MemberStatus
import kotlinx.serialization.Serializable

@Serializable
data class FireStoreDocumentResponse<DocumentResponse: FieldsResponse>(
    val name: String,
    val fields: DocumentResponse,
    val createTime: String,
    val updateTime: String,
) {
    fun toMemberState(): MemberState {
        val castedFields = (fields as? FireStoreMembersFields) ?: return MemberState.error()
        return MemberState(
            name = castedFields.name.stringValue,
            status = MemberStatus.values().find { it.text == castedFields.status.stringValue } ?: MemberStatus.ERROR,
        )
    }
}