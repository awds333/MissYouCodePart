package com.fedchanka.missyou.ui.heart

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fedchanka.missyou.TAG
import com.fedchanka.missyou.service.TokenService
import com.fedchanka.missyou.service.getToken
import kotlinx.coroutines.launch

class HeartViewModel(private val context: Context,
                     private val tokenService: TokenService) : ViewModel() {

    fun logout() {
        viewModelScope.launch {
            context.getToken()?.let { token ->
                tokenService.removeToken(token).ifError {
                    Log.d(TAG, it.toString())
                }
            }
        }
    }
}