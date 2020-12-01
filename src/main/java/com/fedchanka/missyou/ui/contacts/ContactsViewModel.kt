package com.fedchanka.missyou.ui.contacts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fedchanka.missyou.model.domain.ContactData
import com.fedchanka.missyou.model.domain.InvitationData
import com.fedchanka.missyou.service.ContactService
import com.fedchanka.missyou.service.navigation.NavigationAction

class ContactsViewModel(private val contactService: ContactService,
                        private val goToInvitationCreation: NavigationAction
) : ViewModel() {

    private val itemsLiveData = MutableLiveData<List<ContactData>>(emptyList())
    val items: LiveData<List<ContactData>>
        get() = itemsLiveData

    fun onContactClick(contact: ContactData) {

    }

    fun onInvitationClick(invitation: InvitationData) {

    }

    fun onAddContactClick() {
        goToInvitationCreation.navigate()
    }
}