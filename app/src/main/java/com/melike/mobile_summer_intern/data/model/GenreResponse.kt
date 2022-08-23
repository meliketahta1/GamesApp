package com.melike.mobile_summer_intern.data.model

import com.google.gson.annotations.SerializedName

data class GenreResponse(
    @SerializedName("games_count")
    val games_count: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image_background")
    val image_background: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("slug")
    val slug: String?
)
