package com.murta.github_repositories

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.murta.github_repositories.presentation.ui.navigation.RepositoriesRoute
import com.murta.github_repositories.presentation.ui.navigation.pullRequestsGraph
import com.murta.github_repositories.presentation.ui.navigation.repositoriesGraph
import com.murta.github_repositories.presentation.ui.navigation.repositoriesScreen
import com.murta.github_repositories.ui.theme.GitHubRepositoriesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GitHubRepositoriesTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = RepositoriesRoute,
                ) {
                    repositoriesScreen(
                        onRepositoryClicked = {
                            navController.navigate(it)
                        }
                    )

                    repositoriesGraph(
                        onRepositoryClicked = {
                            navController.navigate(it)
                        }
                    )

                    pullRequestsGraph()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Text(
            text = "Hello $name!",
            modifier = modifier.padding(innerPadding)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GitHubRepositoriesTheme {
        Greeting("Android")
    }
}