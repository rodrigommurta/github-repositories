package com.murta.github_repositories.presentation.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.murta.github_repositories.presentation.ui.features.pullrequests.compose.PullRequestsScreenComposable
import kotlinx.serialization.Serializable

@Serializable
data class PullRequestsRoute(
    val title: String,
    val pullsUrl: String,
)

@Serializable
object PullRequestsGraph

fun NavGraphBuilder.pullRequestsGraph() {
    navigation<PullRequestsGraph>(
        startDestination = PullRequestsRoute,
    ) {
        pullRequestsScreen()
    }
}

fun NavGraphBuilder.pullRequestsScreen() {
    composable<PullRequestsRoute> {
        PullRequestsScreenComposable(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
        )
    }
}