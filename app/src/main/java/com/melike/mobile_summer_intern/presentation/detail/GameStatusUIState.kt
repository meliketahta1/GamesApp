package com.melike.mobile_summer_intern.presentation.detail

enum class GameDetailStatus {
    LOADING,
    SUCCESS
}

data class GameStatusUIState(val gameDetailStatus: GameDetailStatus) {

    fun isLoading() = gameDetailStatus == GameDetailStatus.LOADING

    fun isSuccess() = gameDetailStatus == GameDetailStatus.SUCCESS
}
