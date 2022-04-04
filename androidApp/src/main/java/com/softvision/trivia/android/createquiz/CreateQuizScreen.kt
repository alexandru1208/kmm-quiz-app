package com.softvision.trivia.android.createquiz

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.softvision.trivia.android.base.AndroidViewModel
import com.softvision.trivia.domain.entities.Category
import com.softvision.trivia.domain.entities.Difficulty
import com.softvision.trivia.domain.entities.QuestionType
import com.softvision.trivia.createquiz.CreateQuizChange
import com.softvision.trivia.createquiz.CreateQuizIntent
import com.softvision.trivia.createquiz.CreateQuizState
import kotlinx.coroutines.flow.collect
import org.koin.androidx.compose.getViewModel

private const val SINGLE_EVENTS = "single_events"

@Composable
fun CreateQuizScreen(
    viewModel: AndroidViewModel<
            CreateQuizIntent,
            CreateQuizState,
            CreateQuizChange.CreateQuizEvent> = getViewModel(),
    onNavEvent: (CreateQuizNavEvent) -> Unit
) {

    val state: CreateQuizState by viewModel.state.collectAsState(initial = CreateQuizState.INITIAL)

    LaunchedEffect(SINGLE_EVENTS) {
        viewModel.events.collect {
            when (it) {
                is CreateQuizChange.CreateQuizEvent.NavigateToQuizScreen -> onNavEvent(
                    CreateQuizNavEvent.ToAnswerQuizEvent(
                        it.nrOfQuestions,
                        it.category,
                        it.type,
                        it.difficulty
                    )
                )
            }
        }
    }

    if (state.loading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(8.dp)
    ) {
        NumberOfQuestionsSelector(state.nrOfQuestions) {
            viewModel.onIntent(CreateQuizIntent.NumberOfQuestionsChanged(it))
        }
        QuestionTypeSelector(state.selectedType) {
            viewModel.onIntent(CreateQuizIntent.TypeSelected(it))
        }
        DifficultySelector(state.selectedDifficulty) {
            viewModel.onIntent(CreateQuizIntent.DifficultySelected(it))
        }
        CategorySelector(state.categories, state.selectedCategory) {
            viewModel.onIntent(CreateQuizIntent.CategorySelected(it))
        }
        Button(onClick = { viewModel.onIntent(CreateQuizIntent.StartQuizButtonClicked) }) {
            Text(text = "Create Quiz")
        }
    }
}

@Composable
fun NumberOfQuestionsSelector(
    value: Int,
    onChange: (Int) -> Unit
) {
    Column {
        Text(text = "The quiz will contain $value questions")
        Text(text = "Move the slider to change the number of questions")
        Slider(
            value = value.toFloat(),
            onValueChange = { onChange(it.toInt()) },
            valueRange = 0.0f..50.0f,
        )
    }
}

@Composable
fun QuestionTypeSelector(
    selectedType: QuestionType,
    onChange: (type: QuestionType) -> Unit
) {
    Column {
        Text(text = "Select the type of the questions")
        QuestionType.values().forEach {
            Row(modifier = Modifier
                .clickable { onChange(it) }
                .padding(8.dp)) {
                RadioButton(
                    modifier = Modifier.padding(end = 8.dp),
                    selected = it == selectedType,
                    onClick = null
                )
                Text(text = it.name)
            }
        }
    }
}

@Composable
fun DifficultySelector(
    selectedDifficulty: Difficulty,
    onChange: (difficulty: Difficulty) -> Unit
) {
    Column {
        Text(text = "Select the difficulty of the questions")
        Difficulty.values().forEach {
            Row(modifier = Modifier
                .clickable { onChange(it) }
                .padding(8.dp)) {
                RadioButton(
                    modifier = Modifier.padding(end = 8.dp),
                    selected = it == selectedDifficulty,
                    onClick = null
                )
                Text(text = it.name)
            }
        }
    }
}

@Composable
fun CategorySelector(
    categories: List<Category>,
    selectedCategory: Category,
    onChange: (category: Category) -> Unit
) {
    Column {
        Text(text = "Select a category from which you will receive questions")
        categories.forEach {
            Row(modifier = Modifier
                .clickable { onChange(it) }
                .padding(8.dp)) {
                RadioButton(
                    modifier = Modifier.padding(end = 8.dp),
                    selected = it == selectedCategory,
                    onClick = null
                )
                Text(text = it.name)
            }
        }
    }
}

@Preview
@Composable
fun SliderSectionPreview() {
    NumberOfQuestionsSelector(3, {})
}
