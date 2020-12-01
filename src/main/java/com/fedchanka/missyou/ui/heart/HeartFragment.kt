package com.fedchanka.missyou.ui.heart

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.fedchanka.missyou.R
import com.fedchanka.missyou.databinding.FragmentHeartBinding
import com.fedchanka.missyou.service.MyFirebaseMessagingService.Companion.MISS_YOU_ACTION
import com.fedchanka.missyou.service.MyFirebaseMessagingService.Companion.MISS_YOU_TOO_ACTION
import org.koin.androidx.viewmodel.ext.android.viewModel

class HeartFragment : Fragment() {
    private val viewModel: HeartViewModel by viewModel()
    private lateinit var binding: FragmentHeartBinding

    private val intentFilter = IntentFilter().apply {
        addAction(MISS_YOU_ACTION)
        addAction(MISS_YOU_TOO_ACTION)
    }
    private val broadcastReceiver = object : BroadcastReceiver(){
        override fun onReceive(context: Context, intent: Intent) {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_heart, container, false)
        return binding.root
    }

    override fun onStart() {
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        super.onStart()
    }

    override fun onResume() {
        requireActivity().registerReceiver(broadcastReceiver, intentFilter)
        super.onResume()
    }

    override fun onPause() {
        requireActivity().unregisterReceiver(broadcastReceiver)
        super.onPause()
    }
}