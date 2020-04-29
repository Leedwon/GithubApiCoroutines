package com.ledwon.jakub.githubapicoroutines.view.repo_list

import com.ledwon.jakub.githubapicoroutines.CoroutineContextProvider
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val reposModule = module {
    viewModel { parameters ->
        ReposListViewModel(
            repoApi = get(),
            username = parameters[0],
            coroutineContextProvider = CoroutineContextProvider()
        )
    }
}