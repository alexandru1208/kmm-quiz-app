package com.mcg.trivia.createquiz

import com.mcg.trivia.base.mvi.BaseViewModel
import com.mcg.trivia.base.util.CommonFlow
import com.mcg.trivia.base.util.asCommonFlow
import com.mcg.trivia.createquiz.CreateQuizIntent.CategorySelect
import com.mcg.trivia.createquiz.CreateQuizIntent.DifficultySelect
import com.mcg.trivia.createquiz.CreateQuizIntent.NumberOfQuestionsChange
import com.mcg.trivia.createquiz.CreateQuizIntent.StartQuizButtonClick
import com.mcg.trivia.createquiz.CreateQuizIntent.TypeSelect
import com.mcg.trivia.domain.entities.Category
import com.mcg.trivia.domain.usecases.FetchCategoriesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class CreateQuizViewModelImpl(
    private val fetchCategoriesUseCase: FetchCategoriesUseCase
) : BaseViewModel<CreateQuizIntent, CreateQuizState, CreateQuizEvent>() {

    private val categories = MutableStateFlow(emptyList<Category>())
    private val createQuizData = MutableStateFlow(CreateQuizData())
    private val error = MutableStateFlow("")
    private val progressIndicatorVisible = MutableStateFlow(false)

    init {
        coroutineScope.launch {
            progressIndicatorVisible.update { true }
            try {
                val categories = fetchCategoriesUseCase()
                this@CreateQuizViewModelImpl.categories.update { categories }
            } catch (e: Throwable) {
                error.update { "We couldn't fetch categories $e" }
            }
            progressIndicatorVisible.update { false }
        }
    }


    override val state: CommonFlow<CreateQuizState> = combine(
        categories,
        createQuizData,
        error,
        progressIndicatorVisible
    ) { categories, createQuizData, error, progressIndicatorVisible ->
        CreateQuizState(
            loading = progressIndicatorVisible,
            selectedCategory = createQuizData.category,
            categories = categories,
            errorMessage = error.takeIf { it.isNotBlank() },
            nrOfQuestions = createQuizData.nrOfQuestions,
            selectedDifficulty = createQuizData.difficulty,
            selectedType = createQuizData.type
        )
    }.asCommonFlow()

    override fun handleIntent(intent: CreateQuizIntent) {
        when (intent) {
            is CategorySelect -> createQuizData.update { it.copy(category = intent.category) }
            is DifficultySelect -> createQuizData.update { it.copy(difficulty = intent.difficulty) }
            is NumberOfQuestionsChange -> createQuizData.update { it.copy(nrOfQuestions = intent.nrQuestions) }
            is TypeSelect -> createQuizData.update { it.copy(type = intent.type) }

            StartQuizButtonClick -> sendEvent(
                CreateQuizEvent.NavigateToQuizScreen(
                    nrOfQuestions = createQuizData.value.nrOfQuestions,
                    category = createQuizData.value.category,
                    difficulty = createQuizData.value.difficulty,
                    type = createQuizData.value.type
                )
            )
        }
    }
}