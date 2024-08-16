package com.mcg.trivia.sqldelight.dao

import com.mcg.trivia.db.Database
import com.mcg.trivia.domain.entities.Category
import com.mcg.trivia.domain.entities.Difficulty
import com.mcg.trivia.domain.entities.Question
import com.mcg.trivia.domain.entities.QuestionType
import com.mcg.trivia.domain.storage.local.QuestionsDAO
import com.mcg.trivia.sqldelight.mappers.QuestionMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuestionsDAOImpl(database: Database) : QuestionsDAO {

    private val queries = database.questionQueries

    override suspend fun getQuestions(
        nrOfQuestions: Int,
        category: Category,
        type: QuestionType,
        difficulty: Difficulty
    ): List<Question> = withContext(Dispatchers.Default) {
        queries.getQuestions(
            difficulty = difficulty.name,
            categoryName = category.name,
            type = type,
            value_ = nrOfQuestions.toLong(),
        ).executeAsList().map(QuestionMapper::toDomain)
    }

    override suspend fun saveQuestions(questions: List<Question>) =
        withContext(Dispatchers.Default) {
            queries.transaction {
                questions.forEach { queries.insertQuestion(QuestionMapper.fromDomain(it)) }
            }
        }
}