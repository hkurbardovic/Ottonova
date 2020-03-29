package com.hkurbardovic.ottonova.main.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hkurbardovic.ottonova.R
import com.hkurbardovic.ottonova.base.BaseRepository
import com.hkurbardovic.ottonova.di.scopes.FragmentScoped
import com.hkurbardovic.ottonova.network.ApiService
import com.hkurbardovic.ottonova.network.models.HealthPrompt
import com.hkurbardovic.ottonova.network.models.Profile
import com.hkurbardovic.ottonova.network.models.TimelineEvent
import com.hkurbardovic.ottonova.utilities.ConvertUtils
import com.hkurbardovic.ottonova.utilities.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@FragmentScoped
class EventsRepository @Inject constructor(
    private val context: Context,
    private val apiService: ApiService
) : BaseRepository() {

    private val profilesMutableLiveData = MutableLiveData<Event<List<Profile?>?>?>()

    private val healthPromptsMutableLiveData = MutableLiveData<Event<List<HealthPrompt?>?>?>()

    private val timelineEventGroupsMapMutableLiveData =
        MutableLiveData<Map<String, List<TimelineEvent>>>()

    private val errorMessageMutableLiveData = MutableLiveData<Event<String?>?>()

    private val isLoadingMutableLiveData = MutableLiveData<Boolean>()

    val profilesLiveData: LiveData<Event<List<Profile?>?>?> = profilesMutableLiveData

    val healthPromptsLiveData: LiveData<Event<List<HealthPrompt?>?>?> = healthPromptsMutableLiveData

    val timelineEventGroupsMapLiveData: LiveData<Map<String, List<TimelineEvent>>> =
        timelineEventGroupsMapMutableLiveData

    val errorMessageLiveData: LiveData<Event<String?>?> = errorMessageMutableLiveData

    val isLoadingLiveData: LiveData<Boolean> = isLoadingMutableLiveData

    suspend fun getProfiles() {
        withContext(Dispatchers.IO) {
            isLoadingMutableLiveData.postValue(true)
            try {
                val response = apiService.getProfiles()

                if (response.isSuccessful) {
                    profilesMutableLiveData.postValue(Event(response.body()))
                } else {
                    handleException(
                        errorMessageMutableLiveData,
                        response.message(),
                        context.getString(R.string.general_error_message)
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
                handleException(
                    errorMessageMutableLiveData,
                    e.localizedMessage ?: "",
                    context.getString(R.string.general_error_message)
                )
            } finally {
                isLoadingMutableLiveData.postValue(false)
            }
        }
    }

    suspend fun getHealthPrompts(profileId: String) {
        withContext(Dispatchers.IO) {
            isLoadingMutableLiveData.postValue(true)
            try {
                val response = apiService.getHealthPrompts(profileId)

                if (response.isSuccessful) {
                    healthPromptsMutableLiveData.postValue(Event(response.body()))
                } else {
                    handleException(
                        errorMessageMutableLiveData,
                        response.message(),
                        context.getString(R.string.general_error_message)
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
                handleException(
                    errorMessageMutableLiveData,
                    e.localizedMessage ?: "",
                    context.getString(R.string.general_error_message)
                )
            } finally {
                isLoadingMutableLiveData.postValue(false)
            }
        }
    }

    suspend fun getTimelineEvents(profileId: String) {
        withContext(Dispatchers.IO) {
            isLoadingMutableLiveData.postValue(true)
            try {
                val response = apiService.getTimelineEvents(profileId)

                if (response.isSuccessful) {
                    response.body()?.let {
                        timelineEventGroupsMapMutableLiveData.postValue(
                            ConvertUtils.convertTimelineEvents(it)
                        )
                    }
                } else {
                    handleException(
                        errorMessageMutableLiveData,
                        response.message(),
                        context.getString(R.string.general_error_message)
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
                handleException(
                    errorMessageMutableLiveData,
                    e.localizedMessage ?: "",
                    context.getString(R.string.general_error_message)
                )
            } finally {
                isLoadingMutableLiveData.postValue(false)
            }
        }
    }
}