package com.softvision.trivia.base.mvi

import com.softvision.trivia.base.util.CommonFlow
import com.softvision.trivia.base.util.asCommonFlow
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

abstract class BaseViewModel<
        I : Intent,
        S : UIState,
        C : Change,
        SC : Mutation,
        EC : Event> constructor(
    initialState: S,
    private val initialIntent: I? = null
) : CommonViewModel<I, S, EC> {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private var subscribed = false

    private val intentsChannel = Channel<I>(Channel.BUFFERED)
    private val intents = intentsChannel.consumeAsFlow()
    private val _state: MutableStateFlow<S> = MutableStateFlow(initialState)
    private val _events = MutableSharedFlow<EC>()

    override val state: CommonFlow<S> = _state
        .onStart { subscribeToIntentsIfRequired() }
        .asCommonFlow()
    protected val currentState: S
        get() = _state.value
    override val events: CommonFlow<EC> = _events.distinctUntilChanged().asCommonFlow()

    override fun onIntent(intent: I) {
        intentsChannel.trySend(intent)
    }

    @Suppress("UNCHECKED_CAST")
    private fun subscribeToIntentsIfRequired() {
        if (!subscribed) {
            coroutineScope.launch {
                intents
                    .onStart { initialIntent?.let { emit(it) } }
                    .map(::processIntent)
                    .flattenMerge()
                    .collect {
                        when (it) {
                            is Event -> _events.emit(it as EC)
                            is Mutation -> _state.value = mutateState(currentState, it as SC)
                        }
                    }
            }
            subscribed = true
        }
    }

    /**
     * Implement this method to specify how a Change alters the State
     */
    protected abstract fun mutateState(state: S, change: SC): S

    /**
     * Implement this method to specify how a Intent is turned into Changes
     */
    protected abstract fun processIntent(intent: I): Flow<C>

    override fun clear() {
        coroutineScope.cancel()
    }
}