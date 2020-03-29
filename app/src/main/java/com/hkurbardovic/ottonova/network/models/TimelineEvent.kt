package com.hkurbardovic.ottonova.network.models

import com.google.gson.annotations.SerializedName

data class TimelineEvent(
    val uuid: String?,
    val timestamp: String?,
    @SerializedName("display_category") val displayCategory: String?,
    val title: String?,
    val description: String?,
    val category: String?
)