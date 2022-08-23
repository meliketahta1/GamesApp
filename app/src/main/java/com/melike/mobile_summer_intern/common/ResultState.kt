package com.melike.mobile_summer_intern.common

sealed class ResultState<T> {

    class Loading<T> : ResultState<T>()
    data class Success<T>(val data: T) : ResultState<T>()
    data class Error<T>(val exception: T) : ResultState<T>()
}
