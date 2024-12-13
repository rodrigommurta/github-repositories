package com.murta.github_repositories.data.features.pullrequests

import com.murta.github_repositories.data.features.pullrequests.model.PullRequestRemote
import retrofit2.http.GET
import retrofit2.http.Path

interface PullRequestsService {
    @GET("{endpoint}")
    suspend fun getPullRequests(
        @Path("endpoint")
        endpoint: String
    ): List<PullRequestRemote>
}