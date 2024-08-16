package com.mcg.trivia.ktor

import com.mcg.trivia.domain.storage.remote.RemoteStorage
import org.koin.dsl.module

fun networkingModule() = module {
    single { HttpClientFactory.create() }
    single<RemoteStorage> { RemoteStorageImpl(get()) }
}