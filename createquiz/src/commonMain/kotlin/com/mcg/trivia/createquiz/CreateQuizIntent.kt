package com.mcg.trivia.createquiz

import com.mcg.trivia.base.mvi.Intent
import com.mcg.trivia.domain.entities.Category
import com.mcg.trivia.domain.entities.Difficulty
import com.mcg.trivia.domain.entities.QuestionType

sealed class CreateQuizIntent : Intent {
    data class CategorySelect(val category: Category) : CreateQuizIntent()
    data class NumberOfQuestionsChange(val nrQuestions: Int) : CreateQuizIntent()
    data class DifficultySelect(val difficulty: Difficulty) : CreateQuizIntent()
    data class TypeSelect(val type: QuestionType) : CreateQuizIntent()
    data object StartQuizButtonClick : CreateQuizIntent()
}