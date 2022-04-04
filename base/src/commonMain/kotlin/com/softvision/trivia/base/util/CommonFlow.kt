package com.softvision.trivia.base.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

fun <T> Flow<T>.asCommonFlow(): CommonFlow<T> = CommonFlow(this)

class CommonFlow<T>(private val origin: Flow<T>) : Flow<T> by origin {

    constructor() : this(emptyFlow())

    fun watch(block: (T) -> Unit): Closeable {
        val job = Job()

        onEach {
            block(it)
        }.launchIn(CoroutineScope(Dispatchers.Main + job))

        return Closeable(job::cancel)
    }

    fun onValue(block: (T) -> Unit): Closeable {
        val job = Job()

        CoroutineScope(Dispatchers.Main + job).launch {
            block(first())
        }

        return Closeable(job::cancel)
    }
}

fun interface Closeable {
    fun close()
}