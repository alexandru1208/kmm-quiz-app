package com.mcg.trivia.createquiz.di

import com.mcg.trivia.base.mvi.CommonViewModel
import com.mcg.trivia.createquiz.CreateQuizEvent
import com.mcg.trivia.createquiz.CreateQuizIntent
import com.mcg.trivia.createquiz.CreateQuizState
import com.mcg.trivia.createquiz.CreateQuizViewModelImpl
import org.koin.dsl.module

fun createQuizModule() = module {
    factory<CommonViewModel<
            CreateQuizIntent,
            CreateQuizState,
            CreateQuizEvent>> { CreateQuizViewModelImpl(get()) }
}
