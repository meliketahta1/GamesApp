package com.melike.mobile_summer_intern.presentation.feed

import com.melike.mobile_summer_intern.presentation.model.GameResult

interface OnGameItemClicked {

    fun onGameItemClicked(result: GameResult?)
}