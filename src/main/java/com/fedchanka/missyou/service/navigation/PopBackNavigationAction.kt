package com.fedchanka.missyou.service.navigation

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.fedchanka.missyou.service.navigation.NavigationAction

class PopBackNavigationAction(private val fragment: Fragment) : NavigationAction {
    override fun navigate() {
        fragment.findNavController().popBackStack()
    }
}