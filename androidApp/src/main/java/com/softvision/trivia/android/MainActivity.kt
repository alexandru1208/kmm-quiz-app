package com.softvision.trivia.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.softvision.trivia.android.answerquiz.AnswerQuizArgument
import com.softvision.trivia.android.answerquiz.AnswerQuizScreen
import com.softvision.trivia.android.createquiz.CreateQuizNavEvent
import com.softvision.trivia.android.createquiz.CreateQuizScreen

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { App() }
    }
}

sealed class Destination(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList()
) {
    object CreateQuiz : Destination("createquiz")
    object AnswerQuiz : Destination(
        route = "answerquiz/{categoryId}/{nrOfQuestions}/{questionsType}/{questionsDifficulty}",
        arguments = listOf(
            navArgument("categoryId") { type = NavType.LongType },
            navArgument("nrOfQuestions") { type = NavType.IntType },
            navArgument("questionsType") { type = NavType.IntType },
            navArgument("questionsDifficulty") { type = NavType.IntType }
        )
    ) {

        fun createRoute(argument: AnswerQuizArgument) =
            "answerquiz/${argument.categoryId}/${argument.nrOfQuestions}/${argument.questionsType}/${argument.questionsDifficulty}"

        fun getArgument(bundle: Bundle?) = AnswerQuizArgument(
            categoryId = bundle?.getLong("categoryId")!!,
            nrOfQuestions = bundle.getInt("nrOfQuestions"),
            questionsType = bundle.getInt("questionsType"),
            questionsDifficulty = bundle.getInt("questionsDifficulty")
        )
    }
}

@Composable
fun App() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Destination.CreateQuiz.route) {
        composable(Destination.CreateQuiz.route) {
            CreateQuizScreen {
                when (it) {
                    is CreateQuizNavEvent.ToAnswerQuizEvent -> navController.navigate(
                        Destination.AnswerQuiz.createRoute(
                            AnswerQuizArgument(
                                categoryId = it.category.id,
                                nrOfQuestions = it.nrOfQuestions,
                                questionsType = it.type.ordinal,
                                questionsDifficulty = it.difficulty.ordinal
                            )
                        )
                    )
                }
            }
        }
        composable(Destination.AnswerQuiz.route, Destination.AnswerQuiz.arguments) {
            AnswerQuizScreen(argument = Destination.AnswerQuiz.getArgument(it.arguments))
        }
    }
}
