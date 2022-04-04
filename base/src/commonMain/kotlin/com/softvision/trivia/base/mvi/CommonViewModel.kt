package com.softvision.trivia.base.mvi

import com.softvision.trivia.base.util.CommonFlow

interface CommonViewModel<I : Intent, S : UIState, E : Event> {
    val state: CommonFlow<S>
    val events: CommonFlow<E>
    fun onIntent(intent: I)
    fun clear()
}