package com.ledwon.jakub.githubapicoroutines

import org.junit.Assert.*

val <T> T.should: Assertion<T>
    get() = Assertion(this)

class Assertion<T>(
    private val value: T
) {

    fun beEqualTo(other: T) {
        assertEquals(value, other)
    }

    fun Assertion<Boolean>.beTrue() {
        assertTrue(value)
    }

    fun Assertion<Boolean>.beFalse() {
        assertFalse(value)
    }

    fun List<T>.allMeetCondition(condition : (T) -> Boolean){
        this.all { element -> condition.invoke(element) }
    }
}