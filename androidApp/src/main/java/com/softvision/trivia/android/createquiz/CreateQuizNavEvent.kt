package com.softvision.trivia.android.createquiz

import com.softvision.trivia.domain.entities.Category
import com.softvision.trivia.domain.entities.Difficulty
import com.softvision.trivia.domain.entities.QuestionType

sealed class CreateQuizNavEvent {
    data class ToAnswerQuizEvent(
        val nrOfQuestions: Int,
        val category: Category,
        val type: QuestionType,
        val difficulty: Difficulty
    ) : CreateQuizNavEvent()
}
