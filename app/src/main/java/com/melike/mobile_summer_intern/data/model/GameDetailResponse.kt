package com.melike.mobile_summer_intern.data.model

import com.google.gson.annotations.SerializedName

data class GameDetailResponse(
    @SerializedName("achievements_count")
    val achievements_count: Int?,
    @SerializedName("added")
    val added: Int?,
    @SerializedName("additions_count")
    val additions_count: Int?,
    @SerializedName("alternative_names")
    val alternative_names: List<Any>?,
    @SerializedName("background_image")
    val background_image: String?,
    @SerializedName("background_image_additional")
    val background_image_additional: String?,
    @SerializedName("clip")
    val clip: Any?,
    @SerializedName("community_rating")
    val community_rating: Float?,
    @SerializedName("creators_count")
    val creators_count: Int?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("description_raw")
    val description_raw: String?,
    @SerializedName("dominant_color")
    val dominant_color: String?,
    @SerializedName("game_series_count")
    val game_series_count: Int?,
    @SerializedName("genres")
    val genres: List<GenreResponse>?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("metacritic")
    val metacritic: Int?,
    @SerializedName("metacritic_platforms")
    val metacritic_platforms: List<Any>?,
    @SerializedName("metacritic_url")
    val metacritic_url: String?,
    @SerializedName("movies_count")
    val movies_count: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("name_original")
    val name_original: String?,
    @SerializedName("parent_achievements_count")
    val parent_achievements_count: Int?,
    @SerializedName("parents_count")
    val parents_count: Int?,
    @SerializedName("playtime")
    val playtime: Int?,
    @SerializedName("publishers")
    val publishers: List<PublisherResponse>?,
    @SerializedName("rating")
    val rating: Float?,
    @SerializedName("rating_top")
    val rating_top: Float?,
    @SerializedName("ratings_count")
    val ratings_count: Float?,
    @SerializedName("reactions")
    val reactions: Any?,
    @SerializedName("reddit_count")
    val reddit_count: Int?,
    @SerializedName("reddit_description")
    val reddit_description: String?,
    @SerializedName("reddit_logo")
    val reddit_logo: String?,
    @SerializedName("reddit_name")
    val reddit_name: String?,
    @SerializedName("reddit_url")
    val reddit_url: String?,
    @SerializedName("released")
    val released: String?,
    @SerializedName("reviews_count")
    val reviews_count: Int?,
    @SerializedName("reviews_text_count")
    val reviews_text_count: Int?,
    @SerializedName("saturated_color")
    val saturated_color: String?,
    @SerializedName("screenshots_count")
    val screenshots_count: Int?,
    @SerializedName("slug")
    val slug: String?,
    @SerializedName("suggestions_count")
    val suggestions_count: Int?,
    @SerializedName("tba")
    val tba: Boolean?,
    @SerializedName("twitch_count")
    val twitch_count: Int?,
    @SerializedName("updated")
    val updated: String?,
    @SerializedName("user_game")
    val user_game: Any?,
    @SerializedName("website")
    val website: String?,
    @SerializedName("youtube_count")
    val youtube_count: Int?
)