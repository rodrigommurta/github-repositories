package com.murta.github_repositories.presentation.ui.features.pullrequests

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.murta.github_repositories.domain.features.pullrequests.GetPullRequestsUseCase
import com.murta.github_repositories.domain.features.pullrequests.model.PullRequestsScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

const val PULL_REQUESTS_STATE = "PULL_REQUESTS_STATE"

class PullRequestsViewModel(
    private val getPullRequestsUseCase: GetPullRequestsUseCase,
    private val savedState: SavedStateHandle,
) : ViewModel() {
    private val _screen = MutableStateFlow(PullRequestsScreen())
    val screen: StateFlow<PullRequestsScreen> = _screen.asStateFlow()

    init {
        if (savedState.contains(PULL_REQUESTS_STATE).not()) {
            getData()
        } else {
            savedState.get<PullRequestsScreen>(PULL_REQUESTS_STATE)?.let {
                viewModelScope.launch {
                    _screen.emit(it)
                }
            }
        }
    }

    private fun getData() {
        viewModelScope.launch {
            getPullRequestsUseCase(null, screen.value)
                .onEach {
                    savedState[PULL_REQUESTS_STATE] = it
                }
                .collect {
                    _screen.emit(it)
                }
        }
    }
}