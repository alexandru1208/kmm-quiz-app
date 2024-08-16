package com.mcg.trivia.createquiz

import com.mcg.trivia.base.mvi.UIState
import com.mcg.trivia.domain.entities.Category
import com.mcg.trivia.domain.entities.Difficulty
import com.mcg.trivia.domain.entities.QuestionType

data class CreateQuizState(
    val loading: Boolean,
    val selectedCategory: Category,
    val categories: List<Category>,
    val errorMessage: String?,
    val nrOfQuestions: Int,
    val selectedDifficulty: Difficulty,
    val selectedType: QuestionType
) : UIState {
    companion object {
        val INITIAL = CreateQuizState(
            loading = false,
            selectedCategory = Category.ANY,
            categories = emptyList(),
            errorMessage = null,
            nrOfQuestions = 0,
            selectedDifficulty = Difficulty.ANY,
            selectedType = QuestionType.ANY
        )
    }
}