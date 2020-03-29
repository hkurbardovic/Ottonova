package com.hkurbardovic.ottonova.main

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.hkurbardovic.ottonova.R
import com.hkurbardovic.ottonova.base.BaseActivity
import com.hkurbardovic.ottonova.databinding.ActivityMainBinding
import com.hkurbardovic.ottonova.main.fragments.EventsFragment
import com.hkurbardovic.ottonova.network.connectivity.NetworkConnectivityChangeHandler
import com.hkurbardovic.ottonova.network.connectivity.NetworkConnectivityChangeListener
import javax.inject.Inject

class MainActivity : BaseActivity(), NetworkConnectivityChangeListener {

    private lateinit var binding: ActivityMainBinding

    private var networkListener: NetworkListener? = null

    @Inject
    lateinit var networkConnectivityChangeHandler: NetworkConnectivityChangeHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        networkConnectivityChangeHandler.setCallback(this)

        if (savedInstanceState == null) replaceFragment(EventsFragment.newInstance())
    }

    override fun onLost() {
        showMessage(getString(R.string.no_internet_connection), binding.coordinator)
    }

    override fun onAvailable() {
        networkListener?.onAvailable()
    }

    fun showMessage(message: String) {
        showMessage(message, binding.coordinator)
    }

    fun registerNetworkListener(networkListener: NetworkListener) {
        this.networkListener = networkListener
    }

    fun removeNetworkListener() {
        this.networkListener = null
    }

    interface NetworkListener {
        fun onAvailable()
    }
}
