package com.mcg.trivia.answerquiz

data class AnswerQuizArgument(
    val categoryId: Long,
    val questionsType: Int,
    val questionsDifficulty: Int,
    val nrOfQuestions: Int
)