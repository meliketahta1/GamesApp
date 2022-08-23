package com.melike.mobile_summer_intern.data.model

import com.google.gson.annotations.SerializedName

data class PublisherResponse(
    @SerializedName("id")
    val id:Int?,
    @SerializedName("name")
    val name: String?,
)
