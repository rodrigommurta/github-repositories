package com.murta.github_repositories.domain.utils

import kotlinx.coroutines.flow.Flow

abstract class UseCase<K, T> {
    abstract suspend fun execute(
        param: K?,
        currentState: T,
    ): Flow<T>

    suspend operator fun invoke(
        param: K?,
        currentState: T
    ) = execute(param, currentState)
}