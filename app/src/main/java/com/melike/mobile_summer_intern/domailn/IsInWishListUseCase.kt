package com.melike.mobile_summer_intern.domailn

import com.melike.mobile_summer_intern.common.ResultState
import com.melike.mobile_summer_intern.data.GamesAppRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class IsInWishListUseCase @Inject constructor(
    private val repository: GamesAppRepository,
) {
     fun invoke(id: Int?) = flow {
        emit(ResultState.Loading())
        try {
            val isInWishlist = repository.isInWishList(id)
            emit(ResultState.Success(isInWishlist))

        } catch (exception: Exception) {
            emit(ResultState.Error(exception.toString()))
        }
    }
}