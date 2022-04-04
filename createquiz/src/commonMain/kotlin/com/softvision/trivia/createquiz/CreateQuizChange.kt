package com.softvision.trivia.createquiz

import com.softvision.trivia.base.mvi.Change
import com.softvision.trivia.base.mvi.Event
import com.softvision.trivia.base.mvi.Mutation
import com.softvision.trivia.domain.entities.Category
import com.softvision.trivia.domain.entities.Difficulty
import com.softvision.trivia.domain.entities.QuestionType

sealed class CreateQuizChange : Change {

    internal sealed class CreateQuizMutation : CreateQuizChange(), Mutation {
        data class UpdateLoading(val loading: Boolean) : CreateQuizMutation()
        data class DisplayCategories(val categories: List<Category>) : CreateQuizMutation()
        data class DisplayError(val message: String) : CreateQuizMutation()
        data class UpdateNumberOfQuestions(val nrOfQuestions: Int) : CreateQuizMutation()
        data class UpdateDifficulty(val difficulty: Difficulty) : CreateQuizMutation()
        data class UpdateType(val type: QuestionType) : CreateQuizMutation()
        data class UpdateCategory(val category: Category) : CreateQuizMutation()
    }

    sealed class CreateQuizEvent : CreateQuizChange(), Event {
        data class NavigateToQuizScreen(
            val nrOfQuestions: Int,
            val category: Category,
            val type: QuestionType,
            val difficulty: Difficulty
        ) : CreateQuizEvent()
    }
}



