package com.mcg.trivia.ktor.mappers

import com.mcg.trivia.domain.entities.Question
import com.mcg.trivia.ktor.models.APIQuestion

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