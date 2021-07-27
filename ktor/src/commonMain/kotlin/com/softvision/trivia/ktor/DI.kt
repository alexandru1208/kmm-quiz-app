package com.softvision.trivia.ktor

import com.softvision.trivia.domain.storage.remote.RemoteStorage
import org.koin.dsl.module

fun networkingModule() = module {
    single { HttpClientFactory.create() }
    single<RemoteStorage> { RemoteStorageImpl(get()) }
}