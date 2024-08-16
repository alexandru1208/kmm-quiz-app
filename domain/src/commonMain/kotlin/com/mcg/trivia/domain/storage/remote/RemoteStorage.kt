package com.mcg.trivia.domain.storage.remote

import com.mcg.trivia.domain.entities.Category
import com.mcg.trivia.domain.entities.Difficulty
import com.mcg.trivia.domain.entities.Question
import com.mcg.trivia.domain.entities.QuestionType

interface RemoteStorage {

    suspend fun getCategories(): List<Category>

    suspend fun getQuestions(
        nrOfQuestions: Int,
        category: Category,
        type: QuestionType,
        difficulty: Difficulty
    ): List<Question>
}