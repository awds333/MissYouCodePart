package com.fedchanka.missyou.ui.login

import android.util.Log
import com.fedchanka.missyou.R
import com.fedchanka.missyou.TAG
import com.fedchanka.missyou.ui.ActivityResultFragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.GoogleAuthProvider

class GoogleLoginPerformer(private val fragment: ActivityResultFragment) : LoginPerformer {

    private var onSuccess: ((AuthCredential) -> Unit)? = null
    private var onError: ((java.lang.Exception) -> Unit )? = null

    private val googleSignInClient by lazy {
        registerActivityResultListener()

        GoogleSignIn.getClient(
        fragment.requireActivity(), GoogleSignInOptions.Builder(
            GoogleSignInOptions.DEFAULT_SIGN_IN
        )
            .requestIdToken(fragment.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
    )}

    private fun registerActivityResultListener(){
        fragment.onActivityResult { requestCode, _, data ->
            if (requestCode == GOOGLE_SIGN_IN) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                try {
                    val idToken = task.getResult(ApiException::class.java)
                        ?.idToken
                        ?: throw Exception("Id token is null")
                    val credential = GoogleAuthProvider.getCredential(idToken, null)
                    onSuccess?.invoke(credential)
                } catch (e: ApiException) {
                    Log.w(TAG, "Google sign in failed", e)
                    onError?.invoke(e)
                } catch (e: Exception){
                    onError?.invoke(e)
                }
            }
        }
    }

    override fun login(onSuccess: (AuthCredential) -> Unit,
                       onCancel: () -> Unit,
                       onError: (java.lang.Exception) -> Unit) {
        this.onSuccess = onSuccess
        this.onError = onError
        fragment.startActivityForResult(
            googleSignInClient.signInIntent,
            GOOGLE_SIGN_IN
        )
    }

    companion object {
        private const val GOOGLE_SIGN_IN = 34256
    }
}