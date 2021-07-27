package com.softvision.trivia.domain.storage.local

import com.softvision.trivia.domain.entities.Category

interface CategoryDAO {

    suspend fun getCategories(): List<Category>

    suspend fun saveCategories(categories: List<Category>)
}