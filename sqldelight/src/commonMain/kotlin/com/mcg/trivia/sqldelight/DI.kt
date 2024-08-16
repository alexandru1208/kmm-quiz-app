package com.mcg.trivia.sqldelight

import com.mcg.trivia.domain.storage.local.LocalStorage
import org.koin.core.module.Module
import org.koin.dsl.module

fun databaseModule() = module {
    single { DatabaseFactory(get()).create() }
    single<LocalStorage> { LocalStorageImpl(get()) }
} + driverFactoryModule()

expect fun driverFactoryModule(): Module

