package com.softvision.trivia.createquiz

import com.softvision.trivia.base.mvi.Intent
import com.softvision.trivia.domain.entities.Category
import com.softvision.trivia.domain.entities.Difficulty
import com.softvision.trivia.domain.entities.QuestionType

sealed class CreateQuizIntent : Intent {
    object ScreenOpened : CreateQuizIntent()
    data class CategorySelected(val category: Category) : CreateQuizIntent()
    data class NumberOfQuestionsChanged(val nrQuestions: Int) : CreateQuizIntent()
    data class DifficultySelected(val difficulty: Difficulty) : CreateQuizIntent()
    data class TypeSelected(val type: QuestionType) : CreateQuizIntent()
    object StartQuizButtonClicked : CreateQuizIntent()
}