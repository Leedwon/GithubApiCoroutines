package com.ledwon.jakub.networkmodule

import com.ledwon.jakub.model.RepoDetails
import com.ledwon.jakub.model.RepoHeader
import retrofit2.http.GET
import retrofit2.http.Path

interface RepoApi {

    @GET("users/{username}")
    suspend fun getUser(@Path("username") username: String) : RawUser //todo move to user api

    @GET("users/{username}/repos")
    suspend fun getUserRepos(@Path("username")username : String) : List<RepoHeader>

    @GET("repos/{username}/{repo}")
    suspend fun getRepo(@Path("username") username: String, @Path("repo") repo : String) : RepoDetails
}