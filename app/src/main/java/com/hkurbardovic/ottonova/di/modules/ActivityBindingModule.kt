package com.hkurbardovic.ottonova.di.modules

import com.hkurbardovic.ottonova.di.scopes.ActivityScoped
import com.hkurbardovic.ottonova.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
@Suppress("UNUSED")
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [EventsModule::class, EventDetailsModule::class])
    internal abstract fun mainActivity(): MainActivity
}