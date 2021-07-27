package com.softvision.trivia.ktor.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CategoriesResponse(
    @SerialName("trivia_categories")
    val categories: List<CategoryAPI>
)