package com.melike.mobile_summer_intern.data.model

import com.google.gson.annotations.SerializedName

data class ResultResponse(
    @SerializedName("added")
    val added: Int?,
    @SerializedName("background_image")
    val background_image: String?,
    @SerializedName("clip")
    val clip: Any?,
    @SerializedName("dominant_color")
    val dominant_color: String?,
    @SerializedName("esrb_rating")
    val esrb_rating: EsrbRatingResponse?,
    @SerializedName("genres")
    val genres: List<GenreResponse>?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("metacritic")
    val metacritic: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("playtime")
    val playtime: Int?,
    @SerializedName("rating")
    val rating: Double?,
    @SerializedName("rating_top")
    val rating_top: Int?,
    @SerializedName("ratings")
    val ratings: List<RatingResponse>?,
    @SerializedName("ratings_count")
    val ratings_count: Int?,
    @SerializedName("released")
    val released: String?,
    @SerializedName("reviews_count")
    val reviews_count: Int?,
    @SerializedName("reviews_text_count")
    val reviews_text_count: Int?,
    @SerializedName("saturated_color")
    val saturated_color: String?,
    @SerializedName("short_screenshots")
    val short_screenshots: List<ShortScreenshotResponse>?,
    @SerializedName("slug")
    val slug: String?,
    @SerializedName("suggestions_count")
    val suggestions_count: Int?,
    @SerializedName("tba")
    val tba: Boolean?,
    @SerializedName("updated")
    val updated: String?,
    @SerializedName("user_game")
    val user_game: Any?
)
