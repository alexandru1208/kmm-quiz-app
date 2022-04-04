package com.softvision.trivia.domain.entities

data class Category(
    val id: Long,
    val name: String
) {
    companion object {
        val ANY = Category(-1, "Any Category")
    }
}