package com.mcg.trivia.base

import androidx.lifecycle.ViewModel
import com.mcg.trivia.base.mvi.CommonViewModel
import com.mcg.trivia.base.mvi.Event
import com.mcg.trivia.base.mvi.Intent
import com.mcg.trivia.base.mvi.UIState

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