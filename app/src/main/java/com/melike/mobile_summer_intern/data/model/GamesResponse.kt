package com.melike.mobile_summer_intern.data.model

import com.google.gson.annotations.SerializedName

data class GamesResponse(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("next")
    val next: String?,
    @SerializedName("nofollow")
    val nofollow: Boolean?,
    @SerializedName("nofollow_collections")
    val nofollow_collections: List<String>?,
    @SerializedName("noindex")
    val noindex: Boolean?,
    @SerializedName("previous")
    val previous: Any?,
    @SerializedName("results")
    val results: List<ResultResponse>?,
    @SerializedName("seo_description")
    val seo_description: String?,
    @SerializedName("seo_h1")
    val seo_h1: String?,
    @SerializedName("seo_keywords")
    val seo_keywords: String?,
    @SerializedName("seo_title")
    val seo_title: String?
)
