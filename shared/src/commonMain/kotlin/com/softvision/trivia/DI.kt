package com.softvision.trivia

import com.softvision.trivia.domain.domainModule
import com.softvision.trivia.ktor.networkingModule
import com.softvision.trivia.sqldelight.databaseModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            networkingModule() +
                    databaseModule() +
                    domainModule()
        )
    }
