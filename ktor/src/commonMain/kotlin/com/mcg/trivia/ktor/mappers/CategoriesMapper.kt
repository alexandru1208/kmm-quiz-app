package com.mcg.trivia.ktor.mappers

import com.mcg.trivia.domain.entities.Category
import com.mcg.trivia.ktor.models.CategoriesResponse
import com.mcg.trivia.ktor.models.CategoryAPI

object CategoriesMapper {

    fun toDomain(categoriesResponse: CategoriesResponse): List<Category> =
        categoriesResponse.categories.map(::toDomain)

    fun toDomain(categoryAPI: CategoryAPI): Category = categoryAPI.run {
        Category(
            id = id,
            name = name
        )
    }
}