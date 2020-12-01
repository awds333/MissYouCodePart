package com.fedchanka.missyou.service

import android.util.Log
import com.fedchanka.missyou.TAG
import com.fedchanka.missyou.model.EmptySuccess
import com.fedchanka.missyou.model.Result
import com.fedchanka.missyou.model.asError
import com.fedchanka.missyou.model.asSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun <R : Any> asResult(logMessage: String = "operationFailed:",
                               action: suspend () -> R): Result<R, Throwable> {
    return withContext(Dispatchers.IO) {
        try {
            action.invoke().asSuccess<R, Throwable>()
        } catch (t: Throwable) {
            Log.w(TAG, logMessage, t)
            t.asError<R, Throwable>()
        }
    }
}

suspend fun asEmptyResult(logMessage: String = "operationFailed:",
                          action: suspend () -> Unit): Result<Nothing, Throwable> {
    return withContext(Dispatchers.IO) {
        try {
            action.invoke()
            EmptySuccess
        } catch (t: Throwable) {
            Log.w(TAG, logMessage, t)
            t.asError<Nothing, Throwable>()
        }
    }
}