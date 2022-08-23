package com.melike.mobile_summer_intern.presentation.detail

import android.content.Context
import com.melike.mobile_summer_intern.R
import com.melike.mobile_summer_intern.common.util.formatDate
import com.melike.mobile_summer_intern.presentation.model.GameDetailInfoModel
import com.melike.mobile_summer_intern.presentation.model.GameDetailResult


data class GameDetailUiState(
    val gameDetailResult: GameDetailResult = initialResult,
    val descriptionBoxStatus: DescriptionBoxStatus = DescriptionBoxStatus.COLLAPSED
) {

    fun redditLinkVisibility(): Boolean {
        return !gameDetailResult.redditUrl.isEmpty()
    }

    fun websiteLinkVisibility(): Boolean {
        return !gameDetailResult.redditUrl.isEmpty()
    }

    fun metacriticVisibility(): Boolean {
        return gameDetailResult.metacritic != 0
    }

    private fun publisherCheckVisibility(): Boolean {
        return (gameDetailResult.publishers.isEmpty())
    }

    private fun genresCheckVisibility(): Boolean {
        return gameDetailResult.genres.isEmpty()
    }

    private fun releasedDateCheckVisibility(): Boolean {
        return gameDetailResult.released_date.isEmpty()
    }

    private fun playTimeVisibility(): Boolean {
        return gameDetailResult.play_time > 0
    }

    fun getInfoList(context: Context): MutableList<GameDetailInfoModel> {
        val informationList = mutableListOf<GameDetailInfoModel>()
        if (!releasedDateCheckVisibility()) {
            informationList.add(
                GameDetailInfoModel(
                    context.getString(R.string.release_date),
                    gameDetailResult.released_date.formatDate()
                )
            )
        }
        if (!publisherCheckVisibility()) {
            informationList.add(GameDetailInfoModel(
                context.getString(R.string.publishers),
                gameDetailResult.publishers.map { it.name }
                    .joinToString(separator = ", ") { it.toString() })
            )
        }
        if (!genresCheckVisibility()) {
            informationList.add(GameDetailInfoModel(
                "Genres:",
                gameDetailResult.genres.map { it.name }
                    .joinToString(separator = ", ") { it.toString() })
            )
        }
        if (playTimeVisibility()) {
            informationList.add(
                GameDetailInfoModel(
                    context.getString(R.string.play_time),
                    gameDetailResult.play_time.toString() + HOURS
                )
            )
        }
        return informationList
    }


    fun metacriticBackground(): Int {
        gameDetailResult.metacritic.let { metacritic ->
            if (metacritic < 50) {
                return R.drawable.bg_metacritic_high
            }
            if (metacritic in 51..74) {
                return R.drawable.bg_metacritic_low
            }
            if (metacritic > 75) {
                return R.drawable.bg_metacritic_medium
            }
        }
        return 0
    }

    fun getLineNumber(): Int {
        return if (descriptionBoxStatus == DescriptionBoxStatus.COLLAPSED) 4 else Int.MAX_VALUE
    }

    fun changeDescriptionBoxStatus(): GameDetailUiState {
        return if (descriptionBoxStatus == DescriptionBoxStatus.EXTENDED) {
            copy(descriptionBoxStatus = DescriptionBoxStatus.COLLAPSED)
        } else {
            copy(descriptionBoxStatus = DescriptionBoxStatus.EXTENDED)
        }
    }

    companion object {
        const val HOURS = " hours."
        private val initialResult = GameDetailResult(
            id = 0,
            name = "",
            description = "",
            background_image = "",
            metacritic = 0,
            publishers = listOf(),
            genres = listOf(),
            play_time = 0,
            released_date = "",
            redditUrl = "",
            website = ""
        )
    }
}