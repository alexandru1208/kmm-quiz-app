package com.softvision.trivia.domain

import com.softvision.trivia.domain.usecases.FetchCategoriesUseCase
import com.softvision.trivia.domain.usecases.FetchQuestionsUseCase
import org.koin.dsl.module

fun domainModule() = module {
    factory { FetchCategoriesUseCase(get(), get()) }
    factory { FetchQuestionsUseCase(get(), get()) }
}
