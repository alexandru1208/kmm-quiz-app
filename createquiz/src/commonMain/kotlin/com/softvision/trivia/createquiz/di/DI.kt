package com.softvision.trivia.createquiz.di

import com.softvision.trivia.base.mvi.CommonViewModel
import com.softvision.trivia.createquiz.CreateQuizChange
import com.softvision.trivia.createquiz.CreateQuizIntent
import com.softvision.trivia.createquiz.CreateQuizState
import com.softvision.trivia.createquiz.CreateQuizViewModelImpl
import org.koin.dsl.module

fun createQuizModule() = module {
    factory<CommonViewModel<
            CreateQuizIntent,
            CreateQuizState,
            CreateQuizChange.CreateQuizEvent>> { CreateQuizViewModelImpl(get()) }
}
