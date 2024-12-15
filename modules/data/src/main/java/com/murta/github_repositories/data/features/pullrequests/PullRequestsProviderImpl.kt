package com.murta.github_repositories.data.features.pullrequests

import com.murta.github_repositories.data.features.pullrequests.localdb.dao.PullRequestDao
import com.murta.github_repositories.data.features.pullrequests.localdb.entities.toDomain
import com.murta.github_repositories.data.features.pullrequests.model.toDb
import com.murta.github_repositories.data.utils.networkAdapter
import com.murta.github_repositories.domain.features.pullrequests.PullRequestsProvider
import com.murta.github_repositories.domain.features.pullrequests.model.PullRequest
import com.murta.github_repositories.domain.utils.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PullRequestsProviderImpl(
    private val service: PullRequestsService,
    private val dao: PullRequestDao,
) : PullRequestsProvider {
    override suspend fun getScreen(param: String): Flow<State<List<PullRequest>>> =
        networkAdapter(
            query = { dao.queryPullRequests().map { it.toDomain() } },
            get = { service.getPullRequests(param) },
            saveGetResult = { listPullRequestDb ->
                dao.deleteAll()
                dao.insertAll(listPullRequestDb.toDb())
            },
        )
}