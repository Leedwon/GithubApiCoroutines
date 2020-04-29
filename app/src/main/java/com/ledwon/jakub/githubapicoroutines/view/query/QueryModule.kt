package com.ledwon.jakub.githubapicoroutines.view.query

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val queryModule = module {
    viewModel { QueryViewModel() }
}