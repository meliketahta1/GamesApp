package com.melike.mobile_summer_intern.data.model

import com.google.gson.annotations.SerializedName

data class ShortScreenshotResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image")
    val image: String?
)
