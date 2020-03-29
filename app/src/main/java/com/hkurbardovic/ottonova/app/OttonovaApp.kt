package com.hkurbardovic.ottonova.app

import com.hkurbardovic.ottonova.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class OttonovaApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }
}