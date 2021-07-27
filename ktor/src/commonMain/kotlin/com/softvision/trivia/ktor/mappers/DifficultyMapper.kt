package com.softvision.trivia.ktor.mappers

import com.softvision.trivia.domain.entities.Difficulty

object DifficultyMapper {

    fun toDomain(difficulty: String) = when (difficulty) {
        "easy" -> Difficulty.EASY
        "medium" -> Difficulty.MEDIUM
        "hard" -> Difficulty.HARD
        else -> Difficulty.UNKOWN
    }

    fun fromDomain(difficulty: Difficulty) = when (difficulty) {
        Difficulty.EASY -> "easy"
        Difficulty.MEDIUM -> "medium"
        Difficulty.HARD -> "hard"
        else -> ""
    }
}