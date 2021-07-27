package com.softvision.trivia.sqldelight

import com.softvision.trivia.db.Database
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import org.koin.dsl.module

actual fun driverFactoryModule() = module {
    single<SqlDriver> { AndroidSqliteDriver(Database.Schema, get(), DATABASE_NAME) }
}