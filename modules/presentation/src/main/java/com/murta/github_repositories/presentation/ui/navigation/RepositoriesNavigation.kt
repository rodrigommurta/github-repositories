package com.murta.github_repositories.presentation.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.murta.github_repositories.presentation.ui.features.repositories.compose.RepositoriesScreenComposable
import kotlinx.serialization.Serializable

@Serializable
object RepositoriesRoute

@Serializable
object RepositoriesGraph

fun NavGraphBuilder.repositoriesGraph(
    onRepositoryClicked: (PullRequestsRoute) -> Unit,
) {
    navigation<RepositoriesGraph>(
        startDestination = RepositoriesRoute,
    ) {
        repositoriesScreen(
            onRepositoryClicked = onRepositoryClicked
        )
    }
}

fun NavGraphBuilder.repositoriesScreen(
    onRepositoryClicked: (PullRequestsRoute) -> Unit,
) {
    composable<RepositoriesRoute> {
        RepositoriesScreenComposable(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            onRepositoryClicked = onRepositoryClicked
        )
    }
}