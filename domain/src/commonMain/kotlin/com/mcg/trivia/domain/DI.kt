package com.mcg.trivia.domain

import com.mcg.trivia.domain.usecases.FetchCategoriesUseCase
import com.mcg.trivia.domain.usecases.FetchQuestionsUseCase
import org.koin.dsl.module

fun domainModule() = module {
    factory { FetchCategoriesUseCase(get(), get()) }
    factory { FetchQuestionsUseCase(get(), get()) }
}
