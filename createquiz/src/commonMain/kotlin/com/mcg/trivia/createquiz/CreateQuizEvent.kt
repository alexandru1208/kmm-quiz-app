package com.mcg.trivia.createquiz

import com.mcg.trivia.base.mvi.Event
import com.mcg.trivia.domain.entities.Category
import com.mcg.trivia.domain.entities.Difficulty
import com.mcg.trivia.domain.entities.QuestionType

sealed interface CreateQuizEvent :Event{
    data class NavigateToQuizScreen(
        val nrOfQuestions: Int,
        val category: Category,
        val type: QuestionType,
        val difficulty: Difficulty
    ) : CreateQuizEvent
}
