package com.murta.githubrepositories.domain.features.repositories.model

import com.murta.githubrepositories.domain.utils.State
import com.murta.githubrepositories.domain.utils.StateBearer
import java.io.Serializable

data class RepositoriesScreen(
    override val state: State<Any> = State.Loading(),
    val title: String = "",
    val repositories: List<Repository>? = null,
    val pageCount: Int = 1,
) : Serializable, StateBearer
