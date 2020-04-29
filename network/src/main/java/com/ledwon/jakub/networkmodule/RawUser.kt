package com.ledwon.jakub.networkmodule

import com.ledwon.jakub.model.User
import com.squareup.moshi.Json


data class RawUser(
    @Json(name = "login")
    val login: String,
    @field:Json(name = "avatar_url")
    val avatarUrl: String
) {
    fun toUser(): User = User(
        login,
        avatarUrl
    )
}