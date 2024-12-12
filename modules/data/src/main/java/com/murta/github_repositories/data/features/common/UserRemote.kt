package com.murta.github_repositories.data.features.common

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.murta.github_repositories.domain.features.common.User
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserRemote(
    @SerializedName("id") val id: Int,
    @SerializedName("url") val url: String,
    @SerializedName("login") val name: String,
    @SerializedName("avatar_url") val avatarUrl: String,
) : Parcelable

fun UserRemote.toDomain() = User(
    id = id,
    url = url,
    name = name,
    avatarUrl = avatarUrl,
)