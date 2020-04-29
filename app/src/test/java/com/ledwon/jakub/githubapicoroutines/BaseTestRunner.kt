package com.ledwon.jakub.githubapicoroutines

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
open class BaseTestRunner {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
}