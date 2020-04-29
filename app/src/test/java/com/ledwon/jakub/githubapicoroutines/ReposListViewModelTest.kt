package com.ledwon.jakub.githubapicoroutines

import com.gojuno.koptional.None
import com.gojuno.koptional.Some
import com.gojuno.koptional.toOptional
import com.ledwon.jakub.githubapicoroutines.common.ResourceDeferrableString
import com.ledwon.jakub.githubapicoroutines.view.repo_list.ReposListViewModel
import com.ledwon.jakub.model.Owner
import com.ledwon.jakub.model.RepoHeader
import com.ledwon.jakub.model.User
import com.ledwon.jakub.networkmodule.RawUser
import com.ledwon.jakub.networkmodule.RepoApi
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks
import java.net.UnknownHostException

class ReposListViewModelTest : CoroutineTestRunner() {

    @Mock
    private lateinit var repoApiMock: RepoApi

    private lateinit var modelMock: ReposListViewModel

    @Before
    fun before() {
        initMocks(this)
    }

    fun createModel(username: String = "testUsername") {
        modelMock = ReposListViewModel(
            repoApi = repoApiMock,
            username = username,
            coroutineContextProvider = CoroutineContextProvider()
        )
    }

    @Test
    fun `test no internet connection error propagation`() {
        testCoroutineRule.runBlockingTest {
            whenever(repoApiMock.getUser("testUsername")).doAnswer { throw UnknownHostException() }
            whenever(repoApiMock.getUserRepos("testUsername")).doAnswer { throw UnknownHostException() }

            createModel()

            modelMock.error.getOrAwaitValue().should.beEqualTo(
                Some(ResourceDeferrableString(R.string.connection_error))
            )
        }
    }

    @Test
    fun `test init state with happy flow`() {
        testCoroutineRule.runBlockingTest {
            whenever(repoApiMock.getUser("testUsername")).thenReturn(
                RawUser(
                    login = "login",
                    avatarUrl = "www.avatar.com"
                )
            )

            whenever(repoApiMock.getUserRepos("testUsername")).thenReturn(
                listOf(
                    RepoHeader(
                        id = 1,
                        name = "repo1",
                        owner = Owner(
                            login = "login",
                            url = "testUrl"
                        )
                    )
                )
            )

            createModel()

            modelMock.user.getOrAwaitValue().should.beEqualTo(
                User(
                    login = "login",
                    avatarUrl = "www.avatar.com"
                )
            )
            modelMock.userRepos.getOrAwaitValue().size.should.beEqualTo(1)
            modelMock.userRepos.getOrAwaitValue().should.beEqualTo(
                listOf(
                    RepoHeader(
                        id = 1,
                        name = "repo1",
                        owner = Owner(
                            login = "login",
                            url = "testUrl"
                        )
                    )
                )
            )
        }
    }
}