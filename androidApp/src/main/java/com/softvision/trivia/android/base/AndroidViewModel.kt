package com.softvision.trivia.android.base

import androidx.lifecycle.ViewModel
import com.softvision.trivia.base.mvi.CommonViewModel
import com.softvision.trivia.base.mvi.Event
import com.softvision.trivia.base.mvi.Intent
import com.softvision.trivia.base.mvi.UIState

class AndroidViewModel<
        I : Intent,
        S : UIState,
        E : Event>(
    private val commonViewModel: CommonViewModel<I, S, E>
) : ViewModel(), CommonViewModel<I, S, E> by commonViewModel {

    override fun onCleared() {
        commonViewModel.clear()
    }
}