package com.fedchanka.missyou.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fedchanka.missyou.R
import com.fedchanka.missyou.service.LoginService
import com.fedchanka.missyou.service.navigation.NavigationAction
import com.fedchanka.missyou.ui.Toaster
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthWebException
import kotlinx.coroutines.launch

//TODO: finish ui login progress display
//TODO: add single task blocking
class LoginViewModel(private val googleLoginPerformer: LoginPerformer,
                     private val facebookLoginPerformer: LoginPerformer,
                     private val loginService: LoginService,
                     private val toaster: Toaster,
                     private val goToSignUp: NavigationAction,
                     private val finishLogin: NavigationAction
) : ViewModel() {

    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    private val loginWithMailInProgressLiveData = MutableLiveData(false)
    val loginWithMailInProgress: LiveData<Boolean>
        get() = loginWithMailInProgressLiveData

    private val loginWithCredential: (AuthCredential) -> Unit = { credential: AuthCredential ->
        viewModelScope.launch {
            val result = loginService.loginWithCredential(credential)
            result.ifSuccess {
                finishLogin.navigate()
            }
            result.ifError { exception ->
                toaster.message = exception.toUserMessage()
            }
        }
    }

    private val cancelCredentialLogin: () -> Unit = {}

    fun loginWithEmail() {
        val userEmail = email.value ?: return
        val userPassword = password.value ?: return
        loginWithMailInProgressLiveData.value = true
        viewModelScope.launch {
            val result = loginService.loginWithEmail(email = userEmail, password = userPassword)
            result.ifSuccess {
                finishLogin.navigate()
            }
            result.ifError { exception ->
                toaster.message = exception.toUserMessage()
            }
            loginWithMailInProgressLiveData.value = false
        }
    }

    fun loginWithFacebook() =
        facebookLoginPerformer.login(
            onSuccess = loginWithCredential,
            onCancel = cancelCredentialLogin,
            onError = {
                toaster.message = R.string.failed_to_login_with_facebook
                cancelCredentialLogin()
            }
        )

    fun loginWithGoogle() =
        googleLoginPerformer.login(
            onSuccess = loginWithCredential,
            onCancel = cancelCredentialLogin,
            onError = {
                toaster.message = R.string.failed_to_login_with_google
                cancelCredentialLogin()
            }
        )

    fun signUp() = goToSignUp.navigate()

    private fun Throwable.toUserMessage(): Int =
        when (this) {
            is FirebaseAuthInvalidUserException -> R.string.invalid_mail_or_password
            is FirebaseAuthWebException -> R.string.network_error
            else -> R.string.failed_to_login
        }
}