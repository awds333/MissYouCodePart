package com.fedchanka.missyou.service.navigation

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class BackStackIgnoreNavigationAction(private val parent: Fragment,
                                      private val actionId: Int) : NavigationAction {
    override fun navigate() {
        parent.findNavController().navigate(actionId)
    }
}