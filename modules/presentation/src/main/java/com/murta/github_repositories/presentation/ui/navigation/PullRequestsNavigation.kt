package com.murta.github_repositories.presentation.ui.navigation

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.murta.github_repositories.presentation.di.daggerViewModel
import com.murta.github_repositories.presentation.ui.features.pullrequests.PullRequestsViewModel
import com.murta.github_repositories.presentation.ui.features.pullrequests.compose.PullRequestsScreenComposable
import kotlinx.serialization.Serializable

@Serializable
data class PullRequestsRoute(
    val title: String? = null,
    val fullName: String? = null,
)

fun NavGraphBuilder.pullRequestsScreen(
    navController: NavController,
) {
    composable<PullRequestsRoute> { navBackstackEntry ->
        val viewModel: PullRequestsViewModel = daggerViewModel()
        val screen = viewModel.screen.collectAsStateWithLifecycle().value

        val context = LocalContext.current

        PullRequestsScreenComposable(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            screen = screen,
            onPullRequestClicked = { url ->
                context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
            },
            onBottomReached = {
                viewModel.getData(navBackstackEntry.toRoute<PullRequestsRoute>())
            },
            onFeedbackButtonClicked = {
                viewModel.getData(navBackstackEntry.toRoute<PullRequestsRoute>())
            },
            onBackButtonClicked = {
                navController.popBackStack()
            }
        )
    }
}

