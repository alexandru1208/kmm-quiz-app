package com.softvision.trivia.ktor.mappers

import com.softvision.trivia.domain.entities.Category
import com.softvision.trivia.ktor.models.CategoriesResponse
import com.softvision.trivia.ktor.models.CategoryAPI

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