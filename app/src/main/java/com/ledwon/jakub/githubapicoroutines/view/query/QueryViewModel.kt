package com.ledwon.jakub.githubapicoroutines.view.query

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gojuno.koptional.None
import com.gojuno.koptional.Optional
import com.gojuno.koptional.toOptional
import com.ledwon.jakub.githubapicoroutines.R
import com.ledwon.jakub.githubapicoroutines.common.DeferrableString
import com.ledwon.jakub.githubapicoroutines.common.ResourceDeferrableString

class QueryViewModel : ViewModel() {

    private val errorMutable: MutableLiveData<Optional<DeferrableString>> = MutableLiveData(None)
    val error: LiveData<Optional<DeferrableString>> = errorMutable

    private val queryMutable: MutableLiveData<String> = MutableLiveData()
    val query: LiveData<String> = queryMutable

    private val commandMutable: MutableLiveData<Command> = MutableLiveData()
    val command: LiveData<Command> = commandMutable

    fun search() {
        val query = queryMutable.value
        if (query.isNullOrEmpty()) {
            errorMutable.value = ResourceDeferrableString(R.string.query_error).toOptional()
        } else {
            commandMutable.value =
                Command.OpenReposFragment(
                    query
                )
        }
    }

    fun onTextChanged(text: String) {
        if (text.isNotEmpty()) {
            errorMutable.value = None
        }
        queryMutable.value = text
    }

    fun onCommandHandled() {
        commandMutable.value = Command.Noop
    }

    sealed class Command {
        data class OpenReposFragment(val query: String) : Command()
        object Noop : Command()
    }
}