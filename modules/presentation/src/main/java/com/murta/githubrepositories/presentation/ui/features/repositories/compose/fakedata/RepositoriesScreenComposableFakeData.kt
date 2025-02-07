package com.murta.githubrepositories.presentation.ui.features.repositories.compose.fakedata

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.murta.githubrepositories.domain.features.repositories.model.RepositoriesScreen
import com.murta.githubrepositories.domain.features.repositories.model.Repository
import com.murta.githubrepositories.domain.features.user.User
import com.murta.githubrepositories.domain.utils.ErrorInformation
import com.murta.githubrepositories.domain.utils.State

fun repositoryFakeData(id: Int): Repository {
    return Repository(
        id = id,
        repositoryUrl = "https://api.github.com/repos/JetBrains/kotlin",
        pullsUrl = "https://api.github.com/repos/JetBrains/kotlin/pulls",
        name = "kotlin",
        fullName = "JetBrains/kotlin",
        description = "The Kotlin Programming Language. ",
        starsCount = 49565,
        forksCount = 5797,
        owner = User(
            id = 878437,
            url = "https://api.github.com/users/JetBrains",
            name = "JetBrains",
            avatarUrl = "https://avatars.githubusercontent.com/u/878437?v=4"
        )
    )
}

class RepositoriesScreenComposableFakeData : PreviewParameterProvider<RepositoriesScreen> {
    private fun listRepositories(size: Int): List<Repository> = List(size) { index ->
        repositoryFakeData(index)
    }

    private val successScreen = RepositoriesScreen(
        state = State.Success(data = listRepositories(size = 10)),
        title = "GitHub Repositories",
        repositories = listRepositories(size = 10)
    )

    private val loadingScreen = successScreen.copy(state = State.Loading())

    private val errorWithCacheScreen = successScreen.copy(
        state = State.Error(
            error = ErrorInformation(
                message = "Não foi possível conectar ao servidor. Exibindo dados em cache."
            )
        )
    )

    private val errorWithoutCacheScreen = RepositoriesScreen(
        state = State.Error(
            error = ErrorInformation(
                message = "Ocorreu um erro, tente novamente."
            )
        ),
        repositories = null,
    )

    override val values: Sequence<RepositoriesScreen>
        get() = sequenceOf(
            successScreen,
            loadingScreen,
            errorWithCacheScreen,
            errorWithoutCacheScreen
        )
}