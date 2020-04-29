package com.ledwon.jakub.model

data class RepoDetails(
    val id: Int,
    val name: String,
    val owner: Owner,
    val description: String,
    val url: String
)