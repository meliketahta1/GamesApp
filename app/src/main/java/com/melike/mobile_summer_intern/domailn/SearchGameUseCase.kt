package com.melike.mobile_summer_intern.domailn

import androidx.paging.map
import com.melike.mobile_summer_intern.common.ResultState
import com.melike.mobile_summer_intern.data.GamesAppRepository
import com.melike.mobile_summer_intern.domailn.mapper.GameResultMapper
import javax.inject.Inject
import kotlinx.coroutines.flow.flow

class SearchGameUseCase @Inject constructor(
    private val repository: GamesAppRepository,
    private val mapper: GameResultMapper
) {

    suspend fun invoke(query: String) = flow {
        repository.searchGames(query).collect { pagingData ->
            pagingData.map { resultResponse ->
                mapper.mapGameResult(resultResponse)
            }.also { pagingData ->
                emit(pagingData)
            }
        }
    }
}
