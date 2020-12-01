package com.fedchanka.missyou.ui.contacts.invite

import androidx.fragment.app.Fragment
import com.fedchanka.missyou.service.navigation.FragmentProvider
import java.io.Serializable

class InvitationFragmentProvider : FragmentProvider {
    override fun createFragment(arguments: Serializable?): Fragment =
        InvitationFragment.getInstance()

}