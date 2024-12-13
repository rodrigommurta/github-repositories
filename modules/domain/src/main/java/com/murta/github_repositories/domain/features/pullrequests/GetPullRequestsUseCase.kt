package com.murta.github_repositories.domain.features.pullrequests

import com.murta.github_repositories.domain.features.pullrequests.model.PullRequestsScreen
import com.murta.github_repositories.domain.utils.State
import com.murta.github_repositories.domain.utils.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow

class GetPullRequestsUseCase(
    private val provider: PullRequestsProvider,
) : UseCase<String, PullRequestsScreen>() {
    override suspend fun execute(
        param: String?,
        currentState: PullRequestsScreen
    ): Flow<PullRequestsScreen> = flow {
        emit(currentState.copy(state = State.Loading()))

        when (val result = provider.getScreen(param.orEmpty()).firstOrNull()) {
            is State.Success -> {
                emit(
                    currentState.copy(
                        state = State.Success(data = result.data),
                        pullRequests = result.data,
                    )
                )
            }

            is State.Error -> {
                emit(
                    currentState.copy(
                        state = State.Error(data = result.data, error = result.error),
                        pullRequests = result.data,
                    )
                )
            }

            else -> Unit
        }
    }
}