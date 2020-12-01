package com.fedchanka.missyou.service.manager

import android.content.Context
import android.util.Log
import com.fedchanka.missyou.TAG
import com.fedchanka.missyou.model.Result
import com.fedchanka.missyou.service.LoginService
import com.fedchanka.missyou.service.TokenService
import com.fedchanka.missyou.service.asEmptyResult
import com.fedchanka.missyou.service.getToken
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class LoginManager(private val context: Context,
                   private val tokenService: TokenService) : LoginService {

    private val firebaseAuth: FirebaseAuth = Firebase.auth

    override suspend fun loginWithCredential(credential: AuthCredential): Result<Nothing, Throwable> =
        loginByTask(firebaseAuth.signInWithCredential(credential))

    override suspend fun loginWithEmail(email: String, password: String ): Result<Nothing, Throwable> =
        loginByTask(firebaseAuth.signInWithEmailAndPassword(email, password))

    private suspend fun loginByTask(login: Task<AuthResult>): Result<Nothing, Throwable> = asEmptyResult(logMessage = "signIn:failure") {
        login.await()
        context.getToken()?.let { token ->
            tokenService.registerToken(token)
        }
        Log.d(TAG, "signIn:success")
    }

    override suspend fun logout(user: FirebaseUser) {
        context.getToken()?.let { token ->
            tokenService.removeToken(token)
        }
        firebaseAuth.signOut()
    }
}