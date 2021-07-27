package com.softvision.trivia.sqldelight

import com.softvision.trivia.db.Database
import com.squareup.sqldelight.ColumnAdapter
import com.squareup.sqldelight.EnumColumnAdapter
import com.squareup.sqldelight.db.SqlDriver
import softvision.trivia.db.Question

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