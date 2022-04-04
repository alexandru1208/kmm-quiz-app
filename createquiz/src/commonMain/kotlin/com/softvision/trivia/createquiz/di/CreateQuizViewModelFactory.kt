package com.softvision.trivia.createquiz.di

import com.softvision.trivia.base.mvi.CommonViewModel
import com.softvision.trivia.createquiz.CreateQuizChange
import com.softvision.trivia.createquiz.CreateQuizIntent
import com.softvision.trivia.createquiz.CreateQuizState
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

// This is used from Swift
class CreateQuizViewModelFactory : KoinComponent {

    private val viewModel: CommonViewModel<
            CreateQuizIntent,
            CreateQuizState,
            CreateQuizChange.CreateQuizEvent
            > by inject()

    fun create() = viewModel
}