package com.mcg.trivia.sqldelight

import app.cash.sqldelight.ColumnAdapter
import app.cash.sqldelight.EnumColumnAdapter
import app.cash.sqldelight.db.SqlDriver
import com.mcg.trivia.db.Database

import com.mcg.trivia.db.Question

internal const val DATABASE_NAME = "trivia.db"

class DatabaseFactory(private val sqlDriver: SqlDriver) {

    fun create() = Database(
        driver = sqlDriver,
        questionAdapter = Question.Adapter(
            typeAdapter = EnumColumnAdapter(),
            difficultyAdapter = EnumColumnAdapter(),
            wrongAnswersAdapter = object : ColumnAdapter<List<String>, String> {
                override fun decode(databaseValue: String): List<String> =
                    if (databaseValue.isEmpty()) {
                        listOf()
                    } else {
                        databaseValue.split(",")
                    }

                override fun encode(value: List<String>): String =
                    value.joinToString(separator = ",")
            }
        )
    )
}