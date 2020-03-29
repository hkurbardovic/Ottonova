package com.hkurbardovic.ottonova.network.connectivity

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import com.hkurbardovic.ottonova.R
import timber.log.Timber
import javax.inject.Inject

class NetworkConnectivityChangeHandler @Inject constructor(private val context: Context) {

    private lateinit var networkConnectivityChangeListener: NetworkConnectivityChangeListener

    fun setCallback(networkConnectivityChangeListener: NetworkConnectivityChangeListener) {
        this.networkConnectivityChangeListener = networkConnectivityChangeListener
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkRequest = NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .build()
        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)
    }

    private var networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onLost(network: Network?) {
            Timber.d(context.getString(R.string.network_lost))
            networkConnectivityChangeListener.onLost()
        }

        override fun onUnavailable() {
            Timber.d(context.getString(R.string.network_unavailable))
        }

        override fun onLosing(network: Network?, maxMsToLive: Int) {
            Timber.d(context.getString(R.string.network_losing))
        }

        override fun onAvailable(network: Network?) {
            Timber.d(context.getString(R.string.network_available))
            networkConnectivityChangeListener.onAvailable()
        }
    }
}