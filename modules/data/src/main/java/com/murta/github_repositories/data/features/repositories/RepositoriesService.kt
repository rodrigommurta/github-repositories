package com.murta.github_repositories.data.features.repositories

import com.murta.github_repositories.data.features.repositories.model.RepositoryRemote
import retrofit2.http.GET
import retrofit2.http.Path

interface RepositoriesService {
    @GET("{endpoint}")
    suspend fun getRepositories(
        @Path("endpoint")
        endpoint: String
    ): List<RepositoryRemote>
}