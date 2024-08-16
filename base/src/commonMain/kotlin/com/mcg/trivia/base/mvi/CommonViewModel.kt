package com.mcg.trivia.base.mvi

import com.mcg.trivia.base.util.CommonFlow

interface CommonViewModel<I : Intent, S : UIState, E : Event> {
    val state: CommonFlow<S>
    val events: CommonFlow<E>
    fun handleIntent(intent: I)
    fun clear()
}