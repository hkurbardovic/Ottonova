package com.hkurbardovic.ottonova.di.modules

import com.hkurbardovic.ottonova.di.scopes.FragmentScoped
import com.hkurbardovic.ottonova.main.fragments.EventDetailsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
@Suppress("UNUSED")
internal abstract class EventDetailsModule {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun eventDetailsFragment(): EventDetailsFragment
}