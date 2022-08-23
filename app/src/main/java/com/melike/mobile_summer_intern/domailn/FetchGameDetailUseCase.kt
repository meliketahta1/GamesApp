package com.melike.mobile_summer_intern.domailn

import com.melike.mobile_summer_intern.common.ResultState
import com.melike.mobile_summer_intern.data.GamesAppRepository
import com.melike.mobile_summer_intern.domailn.mapper.GameDetailResultMapper
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FetchGameDetailUseCase @Inject constructor(
    private val repository: GamesAppRepository,
    private val mapper: GameDetailResultMapper
) {

    fun invoke(id: Int) = flow {
        emit(ResultState.Loading())
        try {
            emit(
                ResultState.Success(repository.fetchGameDetail(id)
                    ?.let { mapper.mapGameResult(it) })
            )
        } catch (exception: Exception) {
            emit(ResultState.Error(exception.toString()))
        }
    }
}
