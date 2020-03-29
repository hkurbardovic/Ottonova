package com.hkurbardovic.ottonova.di.modules

import com.hkurbardovic.ottonova.di.scopes.FragmentScoped
import com.hkurbardovic.ottonova.main.fragments.EventsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
@Suppress("UNUSED")
internal abstract class EventsModule {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun eventsFragment(): EventsFragment
}