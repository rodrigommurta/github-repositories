package com.murta.githubrepositories.presentation.ui.features.repositories

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.murta.githubrepositories.domain.features.repositories.GetRepositoriesUseCase
import com.murta.githubrepositories.domain.features.repositories.model.RepositoriesScreen
import com.murta.githubrepositories.presentation.di.ViewModelAssistedFactory
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RepositoriesViewModel @AssistedInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val getRepositoriesUseCase: GetRepositoriesUseCase,
) : ViewModel() {
    private val _screen = MutableStateFlow(RepositoriesScreen())
    val screen: StateFlow<RepositoriesScreen> = _screen.asStateFlow()

    init {
        getData()
    }

    fun getData() {
        viewModelScope.launch {
            getRepositoriesUseCase(null, screen.value)
                .collect {
                    _screen.emit(it)
                }
        }
    }

    @AssistedFactory
    interface Factory : ViewModelAssistedFactory<RepositoriesViewModel>
}