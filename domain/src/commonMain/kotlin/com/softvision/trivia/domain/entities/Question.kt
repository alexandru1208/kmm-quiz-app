package com.softvision.trivia.domain.entities

data class Question(
    val categoryName: String,
    val type: QuestionType,
    val difficulty: Difficulty,
    val questionText: String,
    val correctAnswer: String,
    val wrongAnswers: List<String>
)