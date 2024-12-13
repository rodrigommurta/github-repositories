package com.murta.github_repositories.domain.features.repositories

import com.murta.github_repositories.domain.features.repositories.model.RepositoriesScreen
import com.murta.github_repositories.domain.utils.State
import com.murta.github_repositories.domain.utils.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow

class GetRepositoriesUseCase(
    private val provider: RepositoriesProvider,
) : UseCase<String, RepositoriesScreen>() {
    override suspend fun execute(
        param: String?,
        currentState: RepositoriesScreen
    ): Flow<RepositoriesScreen> = flow {
        emit(currentState.copy(state = State.Loading()))

        when (val result = provider.getScreen().firstOrNull()) {
            is State.Success -> {
                emit(
                    currentState.copy(
                        state = State.Success(data = result.data),
                        repositories = result.data,
                    )
                )
            }

            is State.Error -> {
                emit(
                    currentState.copy(
                        state = State.Error(data = result.data, error = result.error),
                        repositories = result.data,
                    )
                )
            }

            else -> Unit
        }
    }
}