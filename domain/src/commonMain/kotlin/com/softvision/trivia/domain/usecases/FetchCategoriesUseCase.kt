package com.softvision.trivia.domain.usecases

import com.softvision.trivia.domain.entities.Category
import com.softvision.trivia.domain.storage.local.LocalStorage
import com.softvision.trivia.domain.storage.remote.RemoteStorage
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class FetchCategoriesUseCase(
    private val localStorage: LocalStorage,
    private val remoteStorage: RemoteStorage
) {

    suspend operator fun invoke(): List<Category> = coroutineScope {
        try {
            val categories = remoteStorage.getCategories()
            launch {
                localStorage.saveCategories(categories)
            }
            return@coroutineScope categories
        } catch (e: Throwable) {
            return@coroutineScope localStorage.getCategories()
        }
    }
}