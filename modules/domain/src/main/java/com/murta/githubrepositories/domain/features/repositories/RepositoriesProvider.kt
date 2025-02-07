package com.murta.githubrepositories.domain.features.repositories

import com.murta.githubrepositories.domain.features.repositories.model.Repository
import com.murta.githubrepositories.domain.utils.State
import kotlinx.coroutines.flow.Flow

interface RepositoriesProvider {
    suspend fun getScreen(pageCount: Int): Flow<State<List<Repository>>>
}