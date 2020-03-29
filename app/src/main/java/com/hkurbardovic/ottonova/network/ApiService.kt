package com.hkurbardovic.ottonova.network

import com.hkurbardovic.ottonova.network.models.HealthPrompt
import com.hkurbardovic.ottonova.network.models.Profile
import com.hkurbardovic.ottonova.network.models.TimelineEvent
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.*

interface ApiService {

    @GET("user/customer/profiles")
    suspend fun getProfiles(): Response<List<Profile?>?>

    @GET("user/customer/profiles/{profile_id}/health-prompts")
    suspend fun getHealthPrompts(
        @Path("profile_id") profileId: String
    ): Response<List<HealthPrompt?>?>

    @GET("user/customer/profiles/{profile_id}/timeline-events")
    suspend fun getTimelineEvents(
        @Path("profile_id") profileId: String
    ): Response<List<TimelineEvent?>?>
}