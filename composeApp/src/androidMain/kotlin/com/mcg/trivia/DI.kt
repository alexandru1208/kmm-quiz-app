package com.mcg.trivia

import com.mcg.trivia.base.AndroidViewModel
import com.mcg.trivia.createquiz.CreateQuizEvent
import com.mcg.trivia.createquiz.CreateQuizIntent
import com.mcg.trivia.createquiz.CreateQuizState
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun androidModule() = module {
    viewModel {
        AndroidViewModel<
                CreateQuizIntent,
                CreateQuizState,
                CreateQuizEvent
                >(get())
    }
}