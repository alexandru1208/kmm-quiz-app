package com.mcg.trivia.sqldelight.mappers

import com.mcg.trivia.domain.entities.Category
import com.mcg.trivia.sqldelight.DBCategory

object CategoryMapper {

    fun toDomain(dbCategory: DBCategory) = dbCategory.run {
        Category(id, name)
    }

    fun fromDomain(category: Category) = category.run {
        DBCategory(id, name)
    }
}