package com.ledwon.jakub.githubapicoroutines

import com.gojuno.koptional.None
import com.gojuno.koptional.toOptional
import com.ledwon.jakub.githubapicoroutines.common.ResourceDeferrableString
import com.ledwon.jakub.githubapicoroutines.view.query.QueryViewModel
import org.junit.Test

class QueryViewModelTest : BaseTestRunner() {

    private lateinit var modelMock: QueryViewModel

    private fun createModel() {
        modelMock = QueryViewModel()
    }

    @Test
    fun testInitialState() {
        createModel()
        modelMock.error.getOrAwaitValue().should.beEqualTo(None)
    }

    @Test
    fun testOnTextChanged() {
        createModel()
        modelMock.onTextChanged("test")
        modelMock.query.getOrAwaitValue().should.beEqualTo("test")
    }

    @Test
    fun testErrorSettingAndResetting() {
        createModel()
        modelMock.search()
        modelMock.error.getOrAwaitValue()
            .should.beEqualTo(ResourceDeferrableString(R.string.query_error).toOptional())
        modelMock.onTextChanged("1")
        modelMock.error.getOrAwaitValue().should.beEqualTo(None)
    }

    @Test
    fun testCommandPropagation() {
        createModel()
        modelMock.onTextChanged("1")
        modelMock.search()
        modelMock.command.getOrAwaitValue()
            .should.beEqualTo(QueryViewModel.Command.OpenReposFragment("1"))
        modelMock.onCommandHandled()
        modelMock.command.getOrAwaitValue().should.beEqualTo(QueryViewModel.Command.Noop)
    }
}