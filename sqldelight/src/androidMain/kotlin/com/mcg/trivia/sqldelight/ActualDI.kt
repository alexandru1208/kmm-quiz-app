package com.mcg.trivia.sqldelight

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.mcg.trivia.db.Database
import org.koin.dsl.module

actual fun driverFactoryModule() = module {
    single<SqlDriver> { AndroidSqliteDriver(Database.Schema, get(), DATABASE_NAME) }
}