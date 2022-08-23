package com.melike.mobile_summer_intern.domailn

import com.melike.mobile_summer_intern.common.ResultState
import com.melike.mobile_summer_intern.data.GamesAppRepository
import com.melike.mobile_summer_intern.domailn.mapper.GameEntityMapper
import com.melike.mobile_summer_intern.presentation.model.GameDetailResult
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddToWishlistUseCase @Inject constructor(
    private val repository: GamesAppRepository,
    private val mapper: GameEntityMapper
) {
     fun invoke(gameResult: GameDetailResult) = flow {
        emit(ResultState.Loading())
        try {
            emit(
                ResultState.Success(
                    repository.addToWishlist(
                        mapper.mapGameEntity(
                            gameResult
                        )
                    )
                )
            )
        } catch (exception: Exception) {
            emit(ResultState.Error(exception.toString()))
        }
    }
}