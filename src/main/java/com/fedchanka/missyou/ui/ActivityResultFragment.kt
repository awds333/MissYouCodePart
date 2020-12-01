package com.fedchanka.missyou.ui

import android.content.Intent
import androidx.fragment.app.Fragment

open class ActivityResultFragment : Fragment() {
    private val activityResultListeners: MutableList<(requestCode: Int, resultCode: Int, data: Intent?) -> Unit> =
        mutableListOf()

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        activityResultListeners.forEach { listener ->
            listener(requestCode, resultCode, data)
        }
    }

    fun onActivityResult(listener: (requestCode: Int, resultCode: Int, data: Intent?) -> Unit) =
        activityResultListeners.add(listener)
}