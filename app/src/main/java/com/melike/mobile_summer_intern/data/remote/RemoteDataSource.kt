package com.melike.mobile_summer_intern.data.remote

import androidx.paging.PagingData
import com.melike.mobile_summer_intern.data.model.GameDetailResponse
import com.melike.mobile_summer_intern.data.model.ResultResponse
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {

    suspend fun fetchGames(): Flow<PagingData<ResultResponse>>

    suspend fun searchGames(query: String): Flow<PagingData<ResultResponse>>

    suspend fun fetchGameDetail(id: Int): GameDetailResponse?

}
