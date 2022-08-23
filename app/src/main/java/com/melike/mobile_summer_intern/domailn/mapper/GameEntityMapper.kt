package com.melike.mobile_summer_intern.domailn.mapper

import com.melike.mobile_summer_intern.presentation.model.GameDetailResult
import com.melike.mobile_summer_intern.presentation.model.GameResult
import javax.inject.Inject

class GameEntityMapper @Inject constructor() {
    fun mapGameEntity(result: GameDetailResult): GameResult {
        return GameResult(
            id = result.id,
            name = result.name,
            background_image = result.background_image
        )
    }
}