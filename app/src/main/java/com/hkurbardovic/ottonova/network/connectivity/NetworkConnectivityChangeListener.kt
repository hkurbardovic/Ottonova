package com.hkurbardovic.ottonova.network.connectivity

interface NetworkConnectivityChangeListener {

    fun onLost()

    fun onAvailable()
}