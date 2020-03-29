package com.hkurbardovic.ottonova.network.models

import com.google.gson.annotations.SerializedName

data class HealthPrompt(
    val uuid: String?,
    val message: String?,
    val permanent: Boolean?,
    @SerializedName("display_category") val displayCategory: String?,
    val metadata: Metadata?,
    val style: Style?
)