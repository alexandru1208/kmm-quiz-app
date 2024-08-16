package com.mcg.trivia.createquiz

import com.mcg.trivia.domain.entities.Category
import com.mcg.trivia.domain.entities.Difficulty
import com.mcg.trivia.domain.entities.QuestionType

data class CreateQuizData(
    val nrOfQuestions: Int = 0,
    val category: Category = Category.ANY,
    val type: QuestionType = QuestionType.ANY,
    val difficulty: Difficulty = Difficulty.ANY
)