package com.mcg.trivia.sqldelight

import com.mcg.trivia.db.Database
import com.mcg.trivia.domain.storage.local.CategoryDAO
import com.mcg.trivia.domain.storage.local.LocalStorage
import com.mcg.trivia.domain.storage.local.QuestionsDAO
import com.mcg.trivia.sqldelight.dao.CategoryDAOImpl
import com.mcg.trivia.sqldelight.dao.QuestionsDAOImpl

class LocalStorageImpl(private val database: Database) : LocalStorage, CategoryDAO
   by CategoryDAOImpl(database), QuestionsDAO by QuestionsDAOImpl(database)