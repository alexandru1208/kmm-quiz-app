package com.softvision.trivia.android

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.softvision.trivia.Greeting
import com.softvision.trivia.domain.entities.Category
import com.softvision.trivia.domain.entities.Difficulty
import com.softvision.trivia.domain.entities.QuestionType
import com.softvision.trivia.domain.storage.remote.RemoteStorage
import com.softvision.trivia.domain.usecases.FetchCategoriesUseCase
import com.softvision.trivia.domain.usecases.FetchQuestionsUseCase
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MainScreen() }
    }
}

@Composable
fun MainScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(greet())
    }
}
