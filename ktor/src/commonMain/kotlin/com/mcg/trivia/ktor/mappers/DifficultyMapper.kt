package com.mcg.trivia.ktor.mappers

import com.mcg.trivia.domain.entities.Difficulty

object DifficultyMapper {

    fun toDomain(difficulty: String) = when (difficulty) {
        "easy" -> Difficulty.EASY
        "medium" -> Difficulty.MEDIUM
        "hard" -> Difficulty.HARD
        else -> Difficulty.ANY
    }

    fun fromDomain(difficulty: Difficulty) = when (difficulty) {
        Difficulty.EASY -> "easy"
        Difficulty.MEDIUM -> "medium"
        Difficulty.HARD -> "hard"
        else -> ""
    }
}