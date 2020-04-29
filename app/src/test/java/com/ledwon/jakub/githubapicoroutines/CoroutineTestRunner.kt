package com.ledwon.jakub.githubapicoroutines

import org.junit.Rule

open class CoroutineTestRunner : BaseTestRunner() {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()
}