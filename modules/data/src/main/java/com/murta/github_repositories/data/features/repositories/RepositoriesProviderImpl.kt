package com.murta.github_repositories.data.features.repositories

import com.murta.github_repositories.data.features.repositories.localdb.dao.RepositoryDao
import com.murta.github_repositories.data.features.repositories.localdb.entities.toDomain
import com.murta.github_repositories.data.features.repositories.model.toDb
import com.murta.github_repositories.data.features.utils.networkAdapter
import com.murta.github_repositories.domain.features.repositories.RepositoriesProvider
import com.murta.github_repositories.domain.features.repositories.model.Repository
import com.murta.github_repositories.domain.utils.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val REPOSITORIES_ENDPOINT = "search/repositories?q=language:Kotlin&sort=stars&page=1"

class RepositoriesProviderImpl(
    private val service: RepositoriesService,
    private val dao: RepositoryDao,
): RepositoriesProvider {
    override suspend fun getScreen(): Flow<State<List<Repository>>> = networkAdapter(
        query = { dao.queryRepositories().map { it.toDomain() } },
        get = { service.getRepositories(REPOSITORIES_ENDPOINT) },
        saveGetResult = { listRepositoryDb ->
            dao.deleteAll()
            dao.insertAll(listRepositoryDb.toDb())
        },
    )
}