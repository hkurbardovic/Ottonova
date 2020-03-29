package com.hkurbardovic.ottonova.di

import com.hkurbardovic.ottonova.app.OttonovaApp
import com.hkurbardovic.ottonova.di.modules.ActivityBindingModule
import com.hkurbardovic.ottonova.di.modules.ContextModule
import com.hkurbardovic.ottonova.di.modules.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ContextModule::class,
        NetworkModule::class,
        ActivityBindingModule::class
    ]
)
interface AppComponent : AndroidInjector<OttonovaApp> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance ottonovaApp: OttonovaApp): AppComponent
    }
}