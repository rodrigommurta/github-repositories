package com.murta.githubrepositories.data.utils

import android.util.Log
import com.murta.githubrepositories.domain.utils.State
import com.murta.githubrepositories.domain.utils.toErrorInformation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

inline fun <Result, Request> networkAdapter(
    crossinline query: () -> Flow<Result>,
    crossinline get: suspend () -> Request,
    crossinline saveGetResult: suspend (Request) -> Unit,
): Flow<State<Result>> = flow {
    var data = query().first()

    try {
        saveGetResult(get())
        data = query().first()

    } catch (error: Exception) {
        Log.e("okhttp_error", error.localizedMessage, error)

        emit(
            State.Error(
                data,
                error.toErrorInformation(),
            )
        )
    }
    emit(State.Success(data))
}