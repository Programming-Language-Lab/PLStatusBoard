package com.pl.domain

import kotlinx.serialization.Serializable

@Serializable
data class WebHookMessage(
    val text: String
)