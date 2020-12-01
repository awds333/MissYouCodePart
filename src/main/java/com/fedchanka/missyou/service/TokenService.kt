package com.fedchanka.missyou.service

import com.fedchanka.missyou.model.Result

interface TokenService {
    suspend fun registerToken(token: String): Result<Nothing, Throwable>
    suspend fun removeToken(token: String): Result<Nothing, Throwable>
}