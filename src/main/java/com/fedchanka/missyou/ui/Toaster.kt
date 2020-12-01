package com.fedchanka.missyou.ui

import android.content.Context
import android.widget.Toast
import kotlin.properties.Delegates.observable

class Toaster(private val context: Context) {
    private var actualToast: Toast? by observable<Toast?>(null) { _, oldToast, newToast ->
        oldToast?.cancel()
        newToast?.show()
    }

    var message: Int = 0
        set(value) {
            field = value
            actualToast = Toast.makeText(context, value, Toast.LENGTH_LONG)
        }
}