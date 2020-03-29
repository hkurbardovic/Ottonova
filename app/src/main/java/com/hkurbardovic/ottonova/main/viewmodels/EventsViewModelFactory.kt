package com.hkurbardovic.ottonova.main.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hkurbardovic.ottonova.di.scopes.FragmentScoped
import com.hkurbardovic.ottonova.main.repositories.EventsRepository
import javax.inject.Inject

/**
 * Factory for creating a [EventsViewModel] with a constructor that takes a [EventsRepository].
 */
@FragmentScoped
class EventsViewModelFactory @Inject constructor(
    private val repository: EventsRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        EventsViewModel(repository) as T
}