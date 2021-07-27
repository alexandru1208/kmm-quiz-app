package com.softvision.trivia.ktor.models

import kotlinx.serialization.Serializable

@Serializable
data class CategoryAPI(
    val id: Long,
    val name: String
)