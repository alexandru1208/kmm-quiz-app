package com.softvision.trivia.domain.storage.local

import com.softvision.trivia.domain.entities.Category
import com.softvision.trivia.domain.entities.Difficulty
import com.softvision.trivia.domain.entities.Question
import com.softvision.trivia.domain.entities.QuestionType

interface QuestionsDAO {

    suspend fun getQuestions(
        nrOfQuestions: Int,
        category: Category,
        type: QuestionType,
        difficulty: Difficulty
    ): List<Question>

    suspend fun saveQuestions(questions: List<Question>)
}