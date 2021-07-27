package com.softvision.trivia.sqldelight.mappers

import com.softvision.trivia.domain.entities.Category
import com.softvision.trivia.sqldelight.DBCategory

object CategoryMapper {

    fun toDomain(dbCategory: DBCategory) = dbCategory.run {
        Category(id, name)
    }

    fun fromDomain(category: Category) = category.run {
        DBCategory(id, name)
    }
}