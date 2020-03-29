package com.hkurbardovic.ottonova.main.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hkurbardovic.ottonova.di.scopes.FragmentScoped
import com.hkurbardovic.ottonova.main.repositories.EventsRepository
import com.hkurbardovic.ottonova.network.models.HealthPrompt
import kotlinx.coroutines.launch
import javax.inject.Inject

@FragmentScoped
class EventsViewModel @Inject constructor(private val repository: EventsRepository) : ViewModel() {

    private val selectedHealthPromptMutableLiveData = MutableLiveData<HealthPrompt?>()

    val profilesLiveData = repository.profilesLiveData

    val healthPromptsLiveData = repository.healthPromptsLiveData

    val timelineEventGroupsMapLiveData = repository.timelineEventGroupsMapLiveData

    val errorMessageLiveData = repository.errorMessageLiveData

    val isLoadingLiveData = repository.isLoadingLiveData

    val selectedHealthPromptLiveData: LiveData<HealthPrompt?> = selectedHealthPromptMutableLiveData

    fun getProfiles() {
        viewModelScope.launch {
            repository.getProfiles()
        }
    }

    fun getHealthPrompts(profileId: String) {
        viewModelScope.launch {
            repository.getHealthPrompts(profileId)
        }
    }

    fun getTimelineEvents(profileId: String) {
        viewModelScope.launch {
            repository.getTimelineEvents(profileId)
        }
    }

    fun postHealthPrompt(healthPrompt: HealthPrompt?) {
        selectedHealthPromptMutableLiveData.value = healthPrompt
    }
}