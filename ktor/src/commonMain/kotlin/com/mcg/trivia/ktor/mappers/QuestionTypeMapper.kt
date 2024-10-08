package com.mcg.trivia.ktor.mappers

import com.mcg.trivia.domain.entities.QuestionType

object QuestionTypeMapper {

    fun fromDomain(type: QuestionType) = when (type) {
        QuestionType.TRUE_FALSE -> "boolean"
        QuestionType.MULTIPLE_CHOICE -> "multiple"
        else -> ""
    }

    fun toDomain(apiType: String) = when (apiType) {
        "boolean" -> QuestionType.TRUE_FALSE
        "multiple" -> QuestionType.MULTIPLE_CHOICE
        else -> QuestionType.ANY
    }
}