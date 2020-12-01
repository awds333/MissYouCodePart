package com.fedchanka.missyou.service.navigation

import androidx.fragment.app.Fragment
import java.io.Serializable

interface FragmentProvider {
    fun createFragment(arguments: Serializable? = null): Fragment
}