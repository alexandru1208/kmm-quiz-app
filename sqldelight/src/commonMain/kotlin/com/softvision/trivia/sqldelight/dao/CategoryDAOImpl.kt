package com.softvision.trivia.sqldelight.dao

import com.softvision.trivia.db.Database
import com.softvision.trivia.domain.entities.Category
import com.softvision.trivia.domain.storage.local.CategoryDAO
import com.softvision.trivia.sqldelight.mappers.CategoryMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CategoryDAOImpl(database: Database) : CategoryDAO {

    private val queries = database.categoryQueries

    override suspend fun getCategories(): List<Category> =
        withContext(Dispatchers.Default) {
            queries.getCategories().executeAsList().map(CategoryMapper::toDomain)
        }


    override suspend fun saveCategories(categories: List<Category>) =
        withContext(Dispatchers.Default) {
            queries.transaction {
                categories.forEach { queries.insertCategoty(CategoryMapper.fromDomain(it)) }
            }
        }
}