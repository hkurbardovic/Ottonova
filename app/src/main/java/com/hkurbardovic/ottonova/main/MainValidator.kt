package com.hkurbardovic.ottonova.main

import com.hkurbardovic.ottonova.network.models.HealthPrompt
import com.hkurbardovic.ottonova.network.models.Profile
import com.hkurbardovic.ottonova.utilities.Event

object MainValidator {

    fun getProfileId(value: Event<List<Profile?>?>?): String? {
        if (value == null || value.hasBeenHandled) return null

        val profiles = value.getContentIfNotHandled()
        if (profiles == null || profiles.isEmpty()) return null

        return profiles[0]?.profileId ?: return null
    }

    fun getHealthPrompt(value: Event<List<HealthPrompt?>?>?): HealthPrompt? {
        if (value == null || value.hasBeenHandled) return null

        val healthPrompts = value.getContentIfNotHandled()
        if (healthPrompts == null || healthPrompts.isEmpty()) return null

        return healthPrompts[0]
    }

    fun getErrorMessage(value: Event<String?>?): String? {
        if (value == null || value.hasBeenHandled) return null
        return value.getContentIfNotHandled()
    }
}