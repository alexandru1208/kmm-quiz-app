package com.mcg.trivia

import com.mcg.trivia.createquiz.di.createQuizModule
import com.mcg.trivia.domain.domainModule
import com.mcg.trivia.ktor.networkingModule
import com.mcg.trivia.sqldelight.databaseModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            networkingModule() +
                    databaseModule() +
                    domainModule() +
                    createQuizModule()
        )
    }

// This is used from Swift
fun initKoin() = initKoin {}