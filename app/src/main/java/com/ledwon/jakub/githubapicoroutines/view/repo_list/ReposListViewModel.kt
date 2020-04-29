package com.ledwon.jakub.githubapicoroutines.view.repo_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gojuno.koptional.None
import com.gojuno.koptional.Optional
import com.gojuno.koptional.toOptional
import com.ledwon.jakub.githubapicoroutines.CoroutineContextProvider
import com.ledwon.jakub.githubapicoroutines.R
import com.ledwon.jakub.githubapicoroutines.common.DeferrableString
import com.ledwon.jakub.githubapicoroutines.common.ResourceDeferrableString
import com.ledwon.jakub.githubapicoroutines.common.toDeferrableString
import com.ledwon.jakub.model.RepoHeader
import com.ledwon.jakub.model.User
import com.ledwon.jakub.networkmodule.RepoApi
import kotlinx.coroutines.*
import java.lang.Exception
import java.net.UnknownHostException
import kotlin.coroutines.CoroutineContext

class ReposListViewModel(
    private val repoApi: RepoApi,
    private val username: String,
    coroutineContextProvider: CoroutineContextProvider
) : ViewModel(), CoroutineScope {

    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext = coroutineContextProvider.IO + job

    private val errorSubject: MutableLiveData<Optional<DeferrableString>> = MutableLiveData(None)
    val error: LiveData<Optional<DeferrableString>> = errorSubject

    private val userReposSubject: MutableLiveData<List<RepoHeader>> = MutableLiveData(emptyList())
    val userRepos: LiveData<List<RepoHeader>> = userReposSubject

    private val userSubject: MutableLiveData<User> = MutableLiveData()
    val user: LiveData<User> = userSubject

    init {
        fetchData()
    }

    fun fetchData() {
        fetchRepos()
        fetchUser()
    }

    private fun fetchRepos() {
        launch {
            try {
                userReposSubject.postValue(repoApi.getUserRepos(username))
            } catch (e: UnknownHostException) {
                errorSubject.postValue(ResourceDeferrableString(R.string.connection_error).toOptional())
            }
        }
    }

    private fun fetchUser() {
        launch {
            try {
                userSubject.postValue(repoApi.getUser(username).toUser())
            } catch (e: UnknownHostException) {
                errorSubject.postValue(ResourceDeferrableString(R.string.connection_error).toOptional())
            }
        }
    }

    override fun onCleared() {
        coroutineContext.cancelChildren()
        super.onCleared()
    }
}