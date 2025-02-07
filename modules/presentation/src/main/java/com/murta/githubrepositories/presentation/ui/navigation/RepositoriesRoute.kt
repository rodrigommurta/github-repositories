package com.murta.githubrepositories.presentation.ui.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.murta.githubrepositories.presentation.di.daggerViewModel
import com.murta.githubrepositories.presentation.ui.features.repositories.RepositoriesViewModel
import com.murta.githubrepositories.presentation.ui.features.repositories.compose.RepositoriesScreenComposable
import kotlinx.serialization.Serializable

@Serializable
object RepositoriesRoute

fun NavGraphBuilder.repositoriesScreen(
    navController: NavHostController,
) {
    composable<RepositoriesRoute> {
        val viewModel: RepositoriesViewModel = daggerViewModel()
        val screen = viewModel.screen.collectAsStateWithLifecycle().value

        RepositoriesScreenComposable(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 24.dp),
            screen = screen,
            onRepositoryClicked = { route ->
                navController.navigate(route)
            },
            onBottomReached = {
                viewModel.getData()
            },
            onFeedbackButtonClicked = {
                viewModel.getData()
            },
        )
    }
}