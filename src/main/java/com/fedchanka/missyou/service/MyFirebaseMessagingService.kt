package com.fedchanka.missyou.service

import android.content.Context
import android.content.Intent
import android.util.Log
import com.fedchanka.missyou.R
import com.fedchanka.missyou.TAG
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MyFirebaseMessagingService : FirebaseMessagingService() {
    private val tokenService: TokenService by inject<TokenServiceImpl>()

    override fun onNewToken(token: String) {
        Firebase.auth.currentUser?.let {
            GlobalScope.launch {
                tokenService.registerToken(token)
            }
        }
        getSharedPreferences(getString(R.string.token_store_key), MODE_PRIVATE).edit().apply {
            putString("token", token)
            apply()
        }
        Log.d(TAG, "Refreshed token: $token")
    }

    override fun onMessageReceived(message: RemoteMessage) {
        message.data.takeIf { data ->
            data.isNotEmpty()
        }?.let { data ->
            when (data[ACTION]) {
                MISS_YOU_SERVER_ACTION -> Intent(MISS_YOU_ACTION)
                MISS_YOU_TOO_SERVER_ACTION -> Intent(MISS_YOU_TOO_ACTION)
                else -> null
            }
        }?.takeIf { intent ->
            intent.resolveActivity(packageManager) != null
        }?.also { intent ->
            sendBroadcast(intent)
        } ?: super.onMessageReceived(message)
    }

    companion object {
        const val MISS_YOU_ACTION = "MISS_YOU_ACTION"
        const val MISS_YOU_TOO_ACTION = "MISS_YOU_TOO_ACTION"

        private const val ACTION = "action"
        private const val MISS_YOU_SERVER_ACTION = "miss_you"
        private const val MISS_YOU_TOO_SERVER_ACTION = "miss_you_too"
    }
}

fun Context.getToken(): String? =
    getSharedPreferences(getString(R.string.token_store_key), Context.MODE_PRIVATE).getString("token","wd")
