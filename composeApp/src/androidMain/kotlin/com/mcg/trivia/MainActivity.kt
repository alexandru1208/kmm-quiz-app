package com.mcg.trivia

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mcg.trivia.createquiz.CreateQuizScreen

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { App() }
    }
}

sealed class Destination(
    val route: String,
) {
    object CreateQuiz : Destination("createquiz")
}

@Composable
fun App() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Destination.CreateQuiz.route) {
        composable(Destination.CreateQuiz.route) {
            CreateQuizScreen()
        }
    }
}
