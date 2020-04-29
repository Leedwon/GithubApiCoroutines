package com.ledwon.jakub.githubapicoroutines.common

import android.content.Context
import androidx.annotation.StringRes

interface DeferrableString {
    fun get(context: Context): String
}

data class ResourceDeferrableString(
    @StringRes private val stringResId: Int
) : DeferrableString {
    override fun get(context: Context): String = context.getString(stringResId)
}

data class ValueDeferrableString(
    val value: String
) : DeferrableString {
    override fun get(context: Context): String = value
}

fun String.toDeferrableString() = ValueDeferrableString(this)