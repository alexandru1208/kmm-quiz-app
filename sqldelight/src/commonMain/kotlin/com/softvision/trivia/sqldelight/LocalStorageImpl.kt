package com.softvision.trivia.sqldelight

import com.softvision.trivia.db.Database
import com.softvision.trivia.domain.storage.local.CategoryDAO
import com.softvision.trivia.domain.storage.local.LocalStorage
import com.softvision.trivia.domain.storage.local.QuestionsDAO
import com.softvision.trivia.sqldelight.dao.CategoryDAOImpl
import com.softvision.trivia.sqldelight.dao.QuestionsDAOImpl

class LocalStorageImpl(private val database: Database) : LocalStorage, CategoryDAO
   by CategoryDAOImpl(database), QuestionsDAO by QuestionsDAOImpl(database)