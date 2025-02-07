package com.murta.githubrepositories.presentation.ui.features.pullrequests

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.murta.githubrepositories.domain.features.pullrequests.GetPullRequestsUseCase
import com.murta.githubrepositories.domain.features.pullrequests.model.PullRequestsScreen
import com.murta.githubrepositories.presentation.di.ViewModelAssistedFactory
import com.murta.githubrepositories.presentation.ui.navigation.PullRequestsRoute
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

private const val TITLE = "title"
private const val FULL_NAME = "fullName"

class PullRequestsViewModel @AssistedInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val getPullRequestsUseCase: GetPullRequestsUseCase,
) : ViewModel() {
    private val _screen = MutableStateFlow(PullRequestsScreen())
    val screen: StateFlow<PullRequestsScreen> = _screen.asStateFlow()

    private val route = savedStateHandle.toRoute<PullRequestsRoute>()

    init {
        getData(route)
    }

    fun getData(route: PullRequestsRoute) {
        val mapParams = mapOf(
            TITLE to route.title.orEmpty(),
            FULL_NAME to route.fullName.orEmpty(),
        )

        viewModelScope.launch {
            getPullRequestsUseCase(
                params = mapParams,
                currentState = screen.value,
            )
                .collect {
                    _screen.emit(it)
                }
        }
    }

    @AssistedFactory
    interface Factory : ViewModelAssistedFactory<PullRequestsViewModel>
}