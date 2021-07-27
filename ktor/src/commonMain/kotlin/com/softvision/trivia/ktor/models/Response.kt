package com.softvision.trivia.ktor.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class Response<T>(
    @SerialName("response_code") val responseCode: Int,
    val results: List<T>
)