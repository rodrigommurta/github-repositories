package com.murta.githubrepositories.domain.features.repositories

import com.murta.githubrepositories.domain.features.repositories.model.RepositoriesScreen
import com.murta.githubrepositories.domain.features.repositories.model.Repository
import com.murta.githubrepositories.domain.utils.State
import com.murta.githubrepositories.domain.utils.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetRepositoriesUseCase @Inject constructor(
    private val provider: RepositoriesProvider,
) : UseCase<String, RepositoriesScreen>() {
    override suspend fun execute(
        params: Map<String, String>?,
        currentState: RepositoriesScreen
    ): Flow<RepositoriesScreen> = flow {
        emit(
            currentState.copy(
                state = State.Loading(),
                title = "GitHub Repositories",
            )
        )

        when (val result = provider.getScreen(currentState.pageCount).firstOrNull()) {
            is State.Success -> {
                emit(
                    currentState.copy(
                        state = State.Success(data = result.data),
                        title = "GitHub Repositories",
                        repositories = maybeFetchRepositories(
                            currentState.repositories.orEmpty(),
                            result.data.orEmpty()
                        ),
                        pageCount = currentState.pageCount + 1,
                    )
                )
            }

            is State.Error -> {
                emit(
                    currentState.copy(
                        state = State.Error(data = result.data, error = result.error),
                        title = "GitHub Repositories",
                        repositories = result.data,
                    )
                )
            }

            else -> Unit
        }
    }

    private fun maybeFetchRepositories(
        currentRepositories: List<Repository>,
        newRepositories: List<Repository>,
    ): List<Repository> {
        return if (currentRepositories.isEmpty()) {
            newRepositories
        } else {
            currentRepositories + newRepositories
        }
    }
}