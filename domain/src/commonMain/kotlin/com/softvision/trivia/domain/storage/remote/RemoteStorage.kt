package com.softvision.trivia.domain.storage.remote

import com.softvision.trivia.domain.entities.Category
import com.softvision.trivia.domain.entities.Difficulty
import com.softvision.trivia.domain.entities.Question
import com.softvision.trivia.domain.entities.QuestionType

interface RemoteStorage {

    suspend fun getCategories(): List<Category>

    suspend fun getQuestions(
        nrOfQuestions: Int,
        category: Category,
        type: QuestionType,
        difficulty: Difficulty
    ): List<Question>
}