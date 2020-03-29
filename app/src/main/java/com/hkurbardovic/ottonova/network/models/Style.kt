package com.hkurbardovic.ottonova.network.models

import com.google.gson.annotations.SerializedName

data class Style(
    @SerializedName("primary_color") val primaryColor: String?,
    @SerializedName("secondary_color") val secondaryColor: String?,
    val image: String?
)