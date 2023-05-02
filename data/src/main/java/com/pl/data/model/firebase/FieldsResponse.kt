package com.pl.data.model.firebase

import kotlinx.serialization.Serializable

interface FieldsResponse

@Serializable
data class FireStoreMembersFields(
    val name: FireStoreFieldValue,
    val status: FireStoreFieldValue,
): FieldsResponse

@Serializable
data class FireStoreFieldValue(
    val stringValue: String,
)