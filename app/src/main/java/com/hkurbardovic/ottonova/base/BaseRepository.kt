package com.hkurbardovic.ottonova.base

import androidx.lifecycle.MutableLiveData
import com.hkurbardovic.ottonova.utilities.Event

abstract class BaseRepository {

    fun handleException(
        errorMessageMutableLiveData: MutableLiveData<Event<String?>?>,
        errorMessage: String,
        generalErrorMessage: String
    ) {
        errorMessageMutableLiveData.postValue(
            if (errorMessage.isNotBlank()) Event(errorMessage) else Event(
                generalErrorMessage
            )
        )
    }
}