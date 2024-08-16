package com.mcg.trivia.createquiz.di

import com.mcg.trivia.base.mvi.CommonViewModel
import com.mcg.trivia.createquiz.CreateQuizEvent
import com.mcg.trivia.createquiz.CreateQuizIntent
import com.mcg.trivia.createquiz.CreateQuizState
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

// This is used from Swift
class CreateQuizViewModelFactory : KoinComponent {

    private val viewModel: CommonViewModel<
            CreateQuizIntent,
            CreateQuizState,
            CreateQuizEvent
            > by inject()

    fun create() = viewModel
}