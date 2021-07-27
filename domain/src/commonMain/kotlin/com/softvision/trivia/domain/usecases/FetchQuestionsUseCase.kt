package com.softvision.trivia.domain.usecases

import com.softvision.trivia.domain.entities.Category
import com.softvision.trivia.domain.entities.Difficulty
import com.softvision.trivia.domain.entities.Question
import com.softvision.trivia.domain.entities.QuestionType
import com.softvision.trivia.domain.storage.local.LocalStorage
import com.softvision.trivia.domain.storage.remote.RemoteStorage
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