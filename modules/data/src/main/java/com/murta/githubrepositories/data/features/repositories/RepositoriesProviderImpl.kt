package com.murta.githubrepositories.data.features.repositories

import com.murta.githubrepositories.data.features.repositories.localdb.dao.RepositoryDao
import com.murta.githubrepositories.data.features.repositories.localdb.entities.toDomain
import com.murta.githubrepositories.data.features.repositories.model.toDb
import com.murta.githubrepositories.data.utils.networkAdapter
import com.murta.githubrepositories.domain.features.repositories.RepositoriesProvider
import com.murta.githubrepositories.domain.features.repositories.model.Repository
import com.murta.githubrepositories.domain.utils.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val LANGUAGE_KEY = "q"
private const val SORT_KEY = "sort"
private const val PAGE_KEY = "page"
private const val LANGUAGE_VALUE = "language:Kotlin"
private const val SORT_VALUE = "stars"

class RepositoriesProviderImpl(
    private val service: RepositoriesService,
    private val dao: RepositoryDao,
) : RepositoriesProvider {
    override suspend fun getScreen(pageCount: Int): Flow<State<List<Repository>>> {
        val query = mapOf(
            LANGUAGE_KEY to LANGUAGE_VALUE,
            SORT_KEY to SORT_VALUE,
            PAGE_KEY to pageCount.toString(),
        )

        return networkAdapter(
            query = { dao.queryRepositories().map { it.toDomain() } },
            get = { service.getRepositories(query) },
            saveGetResult = { listRepositoryDb ->
                dao.deleteAll()
                dao.insertAll(listRepositoryDb.items.toDb())
            },
        )
    }
}