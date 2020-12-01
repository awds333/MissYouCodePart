package com.fedchanka.missyou.ui.contacts.item

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fedchanka.missyou.ui.contacts.OnContactClickListener
import com.fedchanka.missyou.ui.contacts.OnInvitationClickListener

@BindingAdapter("itemsList")
fun setContactsList(view: RecyclerView, items: List<Any>) {
    view.contactsAdapter().items = items
}

@BindingAdapter("onContactClick")
fun setContactClickListener(view: RecyclerView, onContactClick: OnContactClickListener) {
    view.contactsAdapter().onContactClick = onContactClick
}

@BindingAdapter("onInvitationClick")
fun setInvitationClickListener(view: RecyclerView, onInvitationClick: OnInvitationClickListener) {
    view.contactsAdapter().onInvitationClick = onInvitationClick
}

private fun RecyclerView.contactsAdapter(): ContactsAdapter {
    return adapter as? ContactsAdapter ?: ContactsAdapter().also { contactsAdapter ->
        this.adapter = contactsAdapter
    }
}