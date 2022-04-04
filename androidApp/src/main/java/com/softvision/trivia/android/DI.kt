package com.softvision.trivia.android

import com.softvision.trivia.android.base.AndroidViewModel
import com.softvision.trivia.createquiz.CreateQuizChange
import com.softvision.trivia.createquiz.CreateQuizIntent
import com.softvision.trivia.createquiz.CreateQuizState
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun androidModule() = module {
    viewModel {
        AndroidViewModel<
                CreateQuizIntent,
                CreateQuizState,
                CreateQuizChange.CreateQuizEvent
                >(get())
    }
}