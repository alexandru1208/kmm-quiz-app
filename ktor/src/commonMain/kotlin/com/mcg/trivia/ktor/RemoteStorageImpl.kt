package com.mcg.trivia.ktor

import com.mcg.trivia.domain.entities.Category
import com.mcg.trivia.domain.entities.Difficulty
import com.mcg.trivia.domain.entities.Question
import com.mcg.trivia.domain.entities.QuestionType
import com.mcg.trivia.domain.storage.remote.RemoteStorage
import com.mcg.trivia.ktor.RemoteStorageImpl.Companion.Params.AMOUNT
import com.mcg.trivia.ktor.RemoteStorageImpl.Companion.Params.CATEGORY
import com.mcg.trivia.ktor.RemoteStorageImpl.Companion.Params.DIFFICULTY
import com.mcg.trivia.ktor.RemoteStorageImpl.Companion.Params.TYPE
import com.mcg.trivia.ktor.mappers.CategoriesMapper
import com.mcg.trivia.ktor.mappers.DifficultyMapper
import com.mcg.trivia.ktor.mappers.QuestionTypeMapper
import com.mcg.trivia.ktor.mappers.QuestionsMapper
import com.mcg.trivia.ktor.models.APIQuestion
import com.mcg.trivia.ktor.models.CategoriesResponse
import com.mcg.trivia.ktor.models.Response
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class RemoteStorageImpl(private val httpClient: HttpClient) : RemoteStorage {

    companion object {
        private const val QUESTIONS_ENDPOINT = "https://opentdb.com/api.php"
        private const val CATEGORIES_ENDPOINT = "https://opentdb.com/api_category.php"

        private object Params {
            const val DIFFICULTY = "difficulty"
            const val TYPE = "type"
            const val CATEGORY = "category"
            const val AMOUNT = "amount"
        }
    }

    override suspend fun getCategories(): List<Category> {
        val categoriesResponse = httpClient.get(CATEGORIES_ENDPOINT)
        return categoriesResponse.body<CategoriesResponse>()
            .let(CategoriesMapper::toDomain)
    }

    override suspend fun getQuestions(
        nrOfQuestions: Int,
        category: Category,
        type: QuestionType,
        difficulty: Difficulty
    ): List<Question> {
        return httpClient.get(QUESTIONS_ENDPOINT) {
            parameter(AMOUNT, nrOfQuestions)
            parameter(DIFFICULTY, DifficultyMapper.fromDomain(difficulty))
            parameter(CATEGORY, category.id)
            parameter(TYPE, QuestionTypeMapper.fromDomain(type))
        }.body<Response<APIQuestion>>().let(QuestionsMapper::toDomain)
    }
}