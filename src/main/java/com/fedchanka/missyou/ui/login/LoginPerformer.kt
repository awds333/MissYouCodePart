package com.fedchanka.missyou.ui.login

import com.google.firebase.auth.AuthCredential
import java.lang.Exception

interface LoginPerformer {
    fun login(onSuccess: (AuthCredential) -> Unit,
              onCancel: () -> Unit,
              onError: (Exception) -> Unit)
}