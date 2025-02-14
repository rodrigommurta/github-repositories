package com.murta.githubrepositories.presentation.ui.features.pullrequests.compose.fakedata

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.murta.githubrepositories.domain.features.pullrequests.model.PullRequest
import com.murta.githubrepositories.domain.features.pullrequests.model.PullRequestsScreen
import com.murta.githubrepositories.domain.features.user.User
import com.murta.githubrepositories.domain.utils.ErrorInformation
import com.murta.githubrepositories.domain.utils.State

fun pullRequestFakeData(id: Long): PullRequest {
    return PullRequest(
        id = id,
        url = "https://api.github.com/repos/JetBrains/kotlin/pulls/5386",
        state = "open",
        title = "Skip restoring this when generating spill and unspill for coroutines",
        body = "As described in the [associated YouTrack issue]" +
                "(https://youtrack.jetbrains.com/issue/KT-73328/why-this-local-variable-spilling-is-needed-in-Kotlin-Coroutines)，" +
                "there seems to be no need to restore this variable for suspend instance method.\\r\\n",
        date = "2024-12-10T12:41:32Z",
        user = User(
            id = 7991360,
            url = "https://api.github.com/users/tripleCC",
            name = "tripleCC",
            avatarUrl = "https://avatars.githubusercontent.com/u/7991360?v=4"
        )
    )
}

class PullRequestsScreenComposableFakeData : PreviewParameterProvider<PullRequestsScreen> {
    private fun listPullRequests(size: Int): List<PullRequest> = List(size) { index ->
        pullRequestFakeData(index.toLong())
    }

    private val successScreen = PullRequestsScreen(
        state = State.Success(data = listPullRequests(size = 10)),
        title = "kotlin",
        pullRequests = listPullRequests(size = 10)
    )

    private val loadingScreen = successScreen.copy(state = State.Loading())

    private val errorWithCacheScreen = successScreen.copy(
        state = State.Error(
            error = ErrorInformation(
                message = "Não foi possível conectar ao servidor. Exibindo dados em cache."
            )
        )
    )

    private val errorWithoutCacheScreen = PullRequestsScreen(
        state = State.Error(
            error = ErrorInformation(
                message = "Ocorreu um erro, tente novamente."
            )
        ),
        pullRequests = null,
    )

    override val values: Sequence<PullRequestsScreen>
        get() = sequenceOf(
            successScreen,
            loadingScreen,
            errorWithCacheScreen,
            errorWithoutCacheScreen
        )
}