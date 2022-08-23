package com.melike.mobile_summer_intern.presentation.model


data class GameDetailResult(
    val id: Int,
    val name: String,
    val description: String,
    val background_image: String,
    val metacritic: Int,
    val publishers: List<PublisherModel>,
    val genres: List<GenreModel>,
    val play_time: Int,
    val released_date: String,
    val redditUrl: String,
    val website: String
)