package com.softvision.trivia.ktor.mappers

import com.softvision.trivia.domain.entities.Question
import com.softvision.trivia.ktor.models.APIQuestion

object QuestionsMapper : ResponseMapper<APIQuestion, Question>() {

    override fun toDomain(apiModel: APIQuestion): Question = apiModel.run {
        Question(
            categoryName = category,
            type = QuestionTypeMapper.toDomain(type),
            difficulty = DifficultyMapper.toDomain(difficulty),
            questionText = question,
            correctAnswer = correctAnswer,
            wrongAnswers = incorrectAnswers
        )
    }
}