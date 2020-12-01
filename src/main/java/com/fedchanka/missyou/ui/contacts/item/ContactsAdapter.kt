package com.fedchanka.missyou.ui.contacts.item

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fedchanka.missyou.R
import com.fedchanka.missyou.databinding.ItemContactBinding
import com.fedchanka.missyou.ui.contacts.OnContactClickListener
import com.fedchanka.missyou.ui.contacts.OnInvitationClickListener
import kotlin.properties.Delegates

class ContactsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items: List<Any> by Delegates.observable(emptyList()) { _, oldItems, newItems ->
        /*val diffUtil = object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = oldItems.size

            override fun getNewListSize(): Int = newItems.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                oldItems[oldItemPosition].id == newItems[newItemPosition].id

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                oldItems[oldItemPosition] == newItems[newItemPosition]
        }
        val result = DiffUtil.calculateDiff(diffUtil)
        result.dispatchUpdatesTo(this)*/
    }

    var onContactClick: OnContactClickListener? = null
    var onInvitationClick: OnInvitationClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_CONTACT -> {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemContactBinding.inflate(inflater, parent, false)
                ContactViewHolder(binding)
            }
            VIEW_TYPE_DRIVER -> {
                val inflater = LayoutInflater.from(parent.context)
                val view = inflater.inflate(R.layout.driver, parent)
                DriverViewHolder(view)
            }
            else -> throw Exception("unknown view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? ContactViewHolder)?.let { viewHolder ->
            val item = items[position]
            viewHolder.binding.apply {
                //contact = item
                onClick = View.OnClickListener {
                //    onContactClick?.onContactClick(item)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position % 2 == 1)
            VIEW_TYPE_CONTACT
        else
            VIEW_TYPE_DRIVER
    }

    override fun getItemCount() = items.size * 2 - 1

    companion object {
        private val VIEW_TYPE_DRIVER = 1
        private val VIEW_TYPE_CONTACT = 2
    }
}