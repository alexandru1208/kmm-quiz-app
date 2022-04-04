package com.softvision.trivia.createquiz

import com.softvision.trivia.base.mvi.BaseViewModel
import com.softvision.trivia.domain.usecases.FetchCategoriesUseCase
import com.softvision.trivia.createquiz.CreateQuizChange.CreateQuizEvent.NavigateToQuizScreen
import com.softvision.trivia.createquiz.CreateQuizChange.CreateQuizMutation.*
import com.softvision.trivia.createquiz.CreateQuizIntent.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

internal class CreateQuizViewModelImpl(
    private val fetchCategoriesUseCase: FetchCategoriesUseCase
) : BaseViewModel<
        CreateQuizIntent,
        CreateQuizState,
        CreateQuizChange,
        CreateQuizChange.CreateQuizMutation,
        CreateQuizChange.CreateQuizEvent,
        >(CreateQuizState.INITIAL, ScreenOpened) {

    override fun mutateState(
        state: CreateQuizState,
        change: CreateQuizChange.CreateQuizMutation
    ): CreateQuizState {
        println("STATE_CHANGE: $change")
        return when (change) {
            is UpdateLoading -> state.copy(loading = change.loading)
            is DisplayCategories -> state.copy(loading = false, categories = change.categories)
            is DisplayError -> state.copy(loading = false, errorMessage = change.message)
            is UpdateNumberOfQuestions -> state.copy(nrOfQuestions = change.nrOfQuestions)
            is UpdateDifficulty -> state.copy(selectedDifficulty = change.difficulty)
            is UpdateType -> state.copy(selectedType = change.type)
            is UpdateCategory -> state.copy(selectedCategory = change.category)
        }
    }

    override fun processIntent(intent: CreateQuizIntent): Flow<CreateQuizChange> = when (intent) {
        ScreenOpened -> flow {
            emit(UpdateLoading(true))
            try {
                val categories = fetchCategoriesUseCase()
                emit(DisplayCategories(categories))
            } catch (e: Throwable) {
                emit(DisplayError("We couldn't fetch categories $e"))
            }
        }
        is CategorySelected -> flowOf(UpdateCategory(intent.category))
        is DifficultySelected -> flowOf(UpdateDifficulty(intent.difficulty))
        is TypeSelected -> flowOf(UpdateType(intent.type))
        is NumberOfQuestionsChanged -> flowOf(UpdateNumberOfQuestions(intent.nrQuestions))
        StartQuizButtonClicked -> flowOf(with(currentState) {
            NavigateToQuizScreen(
                nrOfQuestions,
                selectedCategory,
                selectedType,
                selectedDifficulty,
            )
        })
    }
}