package com.melike.mobile_summer_intern.domailn

import androidx.paging.map
import com.melike.mobile_summer_intern.data.GamesAppRepository
import com.melike.mobile_summer_intern.domailn.mapper.GameResultMapper
import javax.inject.Inject
import kotlinx.coroutines.flow.flow

class FetchGamesUseCase @Inject constructor(
    private val repository: GamesAppRepository,
    private val mapper: GameResultMapper
) {

    suspend fun invoke() = flow {
        repository.fetchGames().collect { pagingData ->
            pagingData.map { resultResponse ->
                mapper.mapGameResult(resultResponse)
            }.also { gameResult ->
                emit(gameResult)
            }
        }
    }
}