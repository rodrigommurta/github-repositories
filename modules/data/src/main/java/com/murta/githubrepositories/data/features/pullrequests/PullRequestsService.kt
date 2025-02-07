package com.murta.githubrepositories.data.features.pullrequests

import com.murta.githubrepositories.data.features.pullrequests.model.PullRequestRemote
import retrofit2.http.GET
import retrofit2.http.Path

interface PullRequestsService {
    @GET("repos/{param}/pulls")
    suspend fun getPullRequests(
        @Path("param", encoded = true)
        param: String
    ): List<PullRequestRemote>
}