package com.melike.mobile_summer_intern.domailn.mapper

import com.melike.mobile_summer_intern.data.model.ResultResponse
import com.melike.mobile_summer_intern.presentation.model.GameResult
import javax.inject.Inject

class GameResultMapper @Inject constructor() {

    fun mapGameResult(resultResponse: ResultResponse): GameResult {
        return GameResult(
            id = resultResponse.id ?: 0,
            name = resultResponse.name.orEmpty(),
            background_image = resultResponse.background_image.orEmpty()
        )
    }
}