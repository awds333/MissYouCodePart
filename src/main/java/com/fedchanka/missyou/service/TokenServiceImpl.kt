package com.fedchanka.missyou.service

import com.fedchanka.missyou.model.Result
import com.google.firebase.functions.FirebaseFunctions
import kotlinx.coroutines.tasks.await

class TokenServiceImpl : TokenService {

    override suspend fun registerToken(token: String): Result<Nothing, Throwable> = asEmptyResult("failed to register token!") {
        FirebaseFunctions.getInstance().getHttpsCallable("registerToken").call(hashMapOf("token" to token)).await()
    }

    override suspend fun removeToken(token: String): Result<Nothing, Throwable> = asEmptyResult("failed to remove token!") {
        FirebaseFunctions.getInstance().getHttpsCallable("removeToken").call(hashMapOf("token" to token)).await()
    }
}