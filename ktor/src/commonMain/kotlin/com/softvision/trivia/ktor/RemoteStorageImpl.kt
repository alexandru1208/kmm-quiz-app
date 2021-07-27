package com.softvision.trivia.ktor

import com.softvision.trivia.domain.entities.Category
import com.softvision.trivia.domain.entities.Difficulty
import com.softvision.trivia.domain.entities.Question
import com.softvision.trivia.domain.entities.QuestionType
import com.softvision.trivia.domain.storage.remote.RemoteStorage
import com.softvision.trivia.ktor.RemoteStorageImpl.Companion.Params.AMOUNT
import com.softvision.trivia.ktor.RemoteStorageImpl.Companion.Params.CATEGORY
import com.softvision.trivia.ktor.RemoteStorageImpl.Companion.Params.DIFFICULTY
import com.softvision.trivia.ktor.RemoteStorageImpl.Companion.Params.TYPE
import com.softvision.trivia.ktor.mappers.CategoriesMapper
import com.softvision.trivia.ktor.mappers.DifficultyMapper
import com.softvision.trivia.ktor.mappers.QuestionTypeMapper
import com.softvision.trivia.ktor.mappers.QuestionsMapper
import com.softvision.trivia.ktor.models.APIQuestion
import com.softvision.trivia.ktor.models.CategoriesResponse
import com.softvision.trivia.ktor.models.Response
import io.ktor.client.*
import io.ktor.client.request.*

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
        return httpClient.get<CategoriesResponse>(CATEGORIES_ENDPOINT)
            .let(CategoriesMapper::toDomain)
    }

    override suspend fun getQuestions(
        nrOfQuestions: Int,
        category: Category,
        type: QuestionType,
        difficulty: Difficulty
    ): List<Question> {
        return httpClient.get<Response<APIQuestion>>(QUESTIONS_ENDPOINT) {
            parameter(AMOUNT, nrOfQuestions)
            parameter(DIFFICULTY, DifficultyMapper.fromDomain(difficulty))
            parameter(CATEGORY, category.id)
            parameter(TYPE, QuestionTypeMapper.fromDomain(type))
        }.let(QuestionsMapper::toDomain)
    }
}