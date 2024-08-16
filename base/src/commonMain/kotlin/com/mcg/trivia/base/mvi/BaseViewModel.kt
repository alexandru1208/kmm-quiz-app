package com.mcg.trivia.base.mvi

import com.mcg.trivia.base.util.asCommonFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<I : Intent, S : UIState, E : Event> : CommonViewModel<I, S, E> {

    protected val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private val _events = Channel<E>()
    override val events = _events.receiveAsFlow().asCommonFlow()

    protected fun sendEvent(event: E) {
        coroutineScope.launch {
            _events.send(event)
        }
    }

    override fun clear() {
        coroutineScope.cancel()
    }
}