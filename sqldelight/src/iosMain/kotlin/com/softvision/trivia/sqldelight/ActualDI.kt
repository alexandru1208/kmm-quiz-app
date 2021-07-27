package com.softvision.trivia.sqldelight

import com.softvision.trivia.db.Database
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import org.koin.dsl.module

actual fun driverFactoryModule() = module {
    single<SqlDriver> { NativeSqliteDriver(Database.Schema, DATABASE_NAME) }
}