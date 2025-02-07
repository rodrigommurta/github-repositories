package com.murta.githubrepositories.domain.features.pullrequests

import com.murta.githubrepositories.domain.features.pullrequests.model.PullRequestsScreen
import com.murta.githubrepositories.domain.utils.State
import com.murta.githubrepositories.domain.utils.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPullRequestsUseCase @Inject constructor(
    private val provider: PullRequestsProvider,
) : UseCase<String, PullRequestsScreen>() {
    override suspend fun execute(
        params: Map<String, String>?,
        currentState: PullRequestsScreen
    ): Flow<PullRequestsScreen> = flow {
        val title = params?.get("title").orEmpty()
        val param = params?.get("fullName").orEmpty()

        emit(
            currentState.copy(
                state = State.Loading(),
                title = title
            )
        )

        when (val result = provider.getScreen(param).firstOrNull()) {
            is State.Success -> {
                emit(
                    currentState.copy(
                        state = State.Success(data = result.data),
                        title = title,
                        pullRequests = result.data,
                    )
                )
            }

            is State.Error -> {
                emit(
                    currentState.copy(
                        state = State.Error(data = result.data, error = result.error),
                        title = title,
                        pullRequests = result.data,
                    )
                )
            }

            else -> Unit
        }
    }
}