package com.mcg.trivia.sqldelight.mappers

import com.mcg.trivia.domain.entities.Question
import com.mcg.trivia.sqldelight.DBQuestion

object QuestionMapper {

    fun toDomain(dbQuestion: DBQuestion) = dbQuestion.run {
        Question(
            categoryName,
            type,
            difficulty,
            question,
            correctAnswer,
            wrongAnswers
        )
    }

    fun fromDomain(question: Question) = question.run {
        DBQuestion(
            questionText,
            categoryName,
            type,
            difficulty,
            correctAnswer,
            wrongAnswers
        )
    }
}