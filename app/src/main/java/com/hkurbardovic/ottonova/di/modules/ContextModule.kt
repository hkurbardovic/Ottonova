package com.hkurbardovic.ottonova.di.modules

import android.content.Context
import com.hkurbardovic.ottonova.app.OttonovaApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ContextModule {

    @Provides
    @Singleton
    fun provideContext(application: OttonovaApp): Context {
        return application.applicationContext
    }
}