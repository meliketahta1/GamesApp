package com.melike.mobile_summer_intern.data.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.melike.mobile_summer_intern.common.util.Constants
import com.melike.mobile_summer_intern.data.model.GameDetailResponse
import com.melike.mobile_summer_intern.data.network.GamesService
import com.melike.mobile_summer_intern.data.paging.GameResponsePagingSource
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class RemoteDataSourceProvider @Inject constructor(
    private val gamesService: GamesService,
) :
    RemoteDataSource {

    override suspend fun fetchGames() = Pager(PagingConfig(pageSize = 20)) {
        GameResponsePagingSource(gamesService, null)
    }.flow.cachedIn(CoroutineScope(Dispatchers.IO))


    override suspend fun searchGames(query: String) = Pager(PagingConfig(pageSize = 20)) {
        GameResponsePagingSource(gamesService, query)
    }.flow.cachedIn(CoroutineScope(Dispatchers.IO))

    override suspend fun fetchGameDetail(id: Int): GameDetailResponse? =
        gamesService.fetchGameDetail(id, Constants.API_KEY)
}