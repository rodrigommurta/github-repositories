package com.murta.githubrepositories.domain.utils

import kotlinx.coroutines.flow.Flow

abstract class UseCase<K, T> {
    abstract suspend fun execute(
        params: Map<K, K>?,
        currentState: T,
    ): Flow<T>

    suspend operator fun invoke(
        params: Map<K, K>?,
        currentState: T
    ) = execute(params, currentState)
}