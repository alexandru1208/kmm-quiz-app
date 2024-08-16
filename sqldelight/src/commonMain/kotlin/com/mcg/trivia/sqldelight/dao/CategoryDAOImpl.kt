package com.mcg.trivia.sqldelight.dao

import com.mcg.trivia.db.Database
import com.mcg.trivia.domain.entities.Category
import com.mcg.trivia.domain.storage.local.CategoryDAO
import com.mcg.trivia.sqldelight.mappers.CategoryMapper
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