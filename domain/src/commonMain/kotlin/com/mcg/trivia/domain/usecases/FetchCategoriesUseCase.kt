package com.mcg.trivia.domain.usecases

import com.mcg.trivia.domain.entities.Category
import com.mcg.trivia.domain.storage.local.LocalStorage
import com.mcg.trivia.domain.storage.remote.RemoteStorage

class FetchCategoriesUseCase(
    private val localStorage: LocalStorage,
    private val remoteStorage: RemoteStorage
) {

    suspend operator fun invoke(): List<Category> {
        try {
            val categories = remoteStorage.getCategories()
            localStorage.saveCategories(categories)
            localStorage.saveCategories(categories)
            return categories
        } catch (e: Throwable) {
            return localStorage.getCategories()
        }
    }
}