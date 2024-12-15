package com.murta.github_repositories.domain.features.repositories

import com.murta.github_repositories.domain.features.repositories.model.Repository
import com.murta.github_repositories.domain.utils.State
import kotlinx.coroutines.flow.Flow

interface RepositoriesProvider {
    suspend fun getScreen(pageCount: Int): Flow<State<List<Repository>>>
}