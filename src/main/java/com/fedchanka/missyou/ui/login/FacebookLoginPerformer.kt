package com.fedchanka.missyou.ui.login

import android.util.Log
import androidx.fragment.app.Fragment
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.fedchanka.missyou.TAG
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FacebookAuthProvider
import java.lang.Exception

class FacebookLoginPerformer(private val fragment: Fragment) : LoginPerformer {

    private val callbackManager = CallbackManager.Factory.create()

    override fun login(onSuccess: (AuthCredential) -> Unit,
                       onCancel: () -> Unit,
                       onError: (Exception) -> Unit) {
        LoginManager.getInstance().apply {
            registerCallback(callbackManager, object :
                FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult) {
                    Log.d(TAG, "facebook:onSuccess:$loginResult")
                    val token = loginResult.accessToken
                    onSuccess(FacebookAuthProvider.getCredential(token.token))
                }

                override fun onCancel() {
                    Log.d(TAG, "facebook:onCancel")
                    onCancel()
                }

                override fun onError(error: FacebookException) {
                    Log.d(TAG, "facebook:onError", error)
                    onError(error)
                }
            })
            logInWithReadPermissions(fragment, listOf("public_profile"))
        }
    }
}