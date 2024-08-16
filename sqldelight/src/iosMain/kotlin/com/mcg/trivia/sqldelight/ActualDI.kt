package com.mcg.trivia.sqldelight

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.mcg.trivia.db.Database
import org.koin.dsl.module

actual fun driverFactoryModule() = module {
    single<SqlDriver> { NativeSqliteDriver(Database.Schema, DATABASE_NAME) }
}