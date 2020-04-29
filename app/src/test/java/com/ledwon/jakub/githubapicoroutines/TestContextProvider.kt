package com.ledwon.jakub.githubapicoroutines

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class TestContextProvider : CoroutineContextProvider() {
    override val Main: CoroutineContext
        get() = Dispatchers.Unconfined
    override val IO: CoroutineContext
        get() = Dispatchers.Unconfined
}