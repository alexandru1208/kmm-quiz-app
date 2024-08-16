package com.mcg.trivia.domain.storage.local

import com.mcg.trivia.domain.entities.Category

interface CategoryDAO {

    suspend fun getCategories(): List<Category>

    suspend fun saveCategories(categories: List<Category>)
}