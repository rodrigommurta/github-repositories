package com.murta.githubrepositories.data.features.repositories

import com.murta.githubrepositories.data.features.repositories.model.ItemsRemote
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface RepositoriesService {
    @GET("search/repositories")
    suspend fun getRepositories(
        @QueryMap
        endpoint: Map<String, String>,
    ): ItemsRemote
}