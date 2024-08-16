package com.mcg.trivia.domain.storage.local

import com.mcg.trivia.domain.entities.Category
import com.mcg.trivia.domain.entities.Difficulty
import com.mcg.trivia.domain.entities.Question
import com.mcg.trivia.domain.entities.QuestionType

interface QuestionsDAO {

    suspend fun getQuestions(
        nrOfQuestions: Int,
        category: Category,
        type: QuestionType,
        difficulty: Difficulty
    ): List<Question>

    suspend fun saveQuestions(questions: List<Question>)
}