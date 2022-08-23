package com.melike.mobile_summer_intern.domailn

import com.melike.mobile_summer_intern.common.ResultState
import com.melike.mobile_summer_intern.data.GamesAppRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DeleteFromWishlistUseCase @Inject constructor(
    private val repository: GamesAppRepository,
) {
     fun invoke(id: Int) = flow {
        emit(ResultState.Loading())
        try {
            emit(
                ResultState.Success(
                    repository.removeFromWishlist(
                        id
                    )
                )
            )
        } catch (exception: Exception) {
            emit(ResultState.Error(exception.toString()))
        }
    }
}