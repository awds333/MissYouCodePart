package com.fedchanka.missyou.service

import com.fedchanka.missyou.model.Result
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseUser

interface LoginService {
    suspend fun loginWithCredential(credential: AuthCredential): Result<Nothing, Throwable>
    suspend fun loginWithEmail(email: String, password: String): Result<Nothing, Throwable>
    suspend fun logout(user: FirebaseUser)
}