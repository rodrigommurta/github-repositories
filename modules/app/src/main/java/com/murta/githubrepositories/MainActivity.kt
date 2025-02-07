package com.murta.githubrepositories

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.murta.githubrepositories.presentation.di.Inject
import com.murta.githubrepositories.presentation.ui.navigation.RepositoriesRoute
import com.murta.githubrepositories.presentation.ui.navigation.pullRequestsScreen
import com.murta.githubrepositories.presentation.ui.navigation.repositoriesScreen
import com.murta.githubrepositories.ui.theme.GitHubRepositoriesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            GitHubRepositoriesTheme {
                Inject(GitHubRepositoriesApplication.app.getViewModelFactory()) {
                    NavHost(
                        navController = navController,
                        startDestination = RepositoriesRoute,
                    ) {
                        repositoriesScreen(
                            navController = navController,
                        )

                        pullRequestsScreen(
                            navController = navController,
                        )
                    }
                }
            }
        }
    }
}