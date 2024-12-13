package com.murta.github_repositories.presentation.ui.features.repositories

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.murta.github_repositories.domain.features.repositories.GetRepositoriesUseCase
import com.murta.github_repositories.domain.features.repositories.model.RepositoriesScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

const val REPOSITORIES_STATE = "REPOSITORIES_STATE"

class RepositoriesViewModel(
    private val getRepositoriesUseCase: GetRepositoriesUseCase,
    private val savedState: SavedStateHandle,
) : ViewModel() {
    private val _screen = MutableStateFlow(RepositoriesScreen())
    val screen: StateFlow<RepositoriesScreen> = _screen.asStateFlow()

    init {
        if (savedState.contains(REPOSITORIES_STATE).not()) {
            getData()
        } else {
            savedState.get<RepositoriesScreen>(REPOSITORIES_STATE)?.let {
                viewModelScope.launch {
                    _screen.emit(it)
                }
            }
        }
    }

    private fun getData() {
        viewModelScope.launch {
            getRepositoriesUseCase(null, screen.value)
                .onEach {
                    savedState[REPOSITORIES_STATE] = it
                }
                .collect {
                    _screen.emit(it)
                }
        }
    }
}