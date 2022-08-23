package com.melike.mobile_summer_intern.domailn.mapper

import com.melike.mobile_summer_intern.data.model.GameDetailResponse
import com.melike.mobile_summer_intern.presentation.model.GameDetailResult

import javax.inject.Inject

class GameDetailResultMapper @Inject constructor(
    private val genreModelMapper: GenreModelMapper,
    private val publisherModelMapper: PublisherModelMapper
) {
    fun mapGameResult(resultResponse: GameDetailResponse): GameDetailResult {
        return GameDetailResult(
            id = resultResponse.id ?: 0,
            name = resultResponse.name.orEmpty(),
            description = resultResponse.description.orEmpty(),
            background_image = resultResponse.background_image.orEmpty(),
            metacritic = resultResponse.metacritic ?: 0,
            genres = genreModelMapper.mapGenreResponse(resultResponse.genres),
            publishers = publisherModelMapper.mapPublisherModel(resultResponse.publishers),
            released_date = resultResponse.released.orEmpty(),
            play_time = resultResponse.playtime ?: 0,
            redditUrl = resultResponse.reddit_url.orEmpty(),
            website = resultResponse.website.orEmpty()
        )
    }
}