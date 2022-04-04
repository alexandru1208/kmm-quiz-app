package com.softvision.trivia.android.answerquiz

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun AnswerQuizScreen(argument: AnswerQuizArgument) {
    Text(text ="$argument")
}