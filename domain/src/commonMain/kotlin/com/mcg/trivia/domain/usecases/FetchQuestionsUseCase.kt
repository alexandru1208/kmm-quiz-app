package com.mcg.trivia.domain.usecases

import com.mcg.trivia.domain.entities.Category
import com.mcg.trivia.domain.entities.Difficulty
import com.mcg.trivia.domain.entities.Question
import com.mcg.trivia.domain.entities.QuestionType
import com.mcg.trivia.domain.storage.local.LocalStorage
import com.mcg.trivia.domain.storage.remote.RemoteStorage
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class FetchQuestionsUseCase(
    private val localStorage: LocalStorage,
    private val remoteStorage: RemoteStorage
) {
    suspend operator fun invoke(
        nrOfQuestions: Int,
        category: Category,
        type: QuestionType,
        difficulty: Difficulty
    ): List<Question> = coroutineScope {
        try {
            val questions = remoteStorage.getQuestions(nrOfQuestions, category, type, difficulty)
            launch {
                localStorage.saveQuestions(questions)
            }
            return@coroutineScope questions
        } catch (e: Throwable) {
            return@coroutineScope localStorage.getQuestions(
                nrOfQuestions,
                category,
                type,
                difficulty
            )
        }
    }
}