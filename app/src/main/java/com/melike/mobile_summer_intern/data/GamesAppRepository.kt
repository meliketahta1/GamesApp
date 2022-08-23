package com.melike.mobile_summer_intern.data

import com.melike.mobile_summer_intern.data.local.LocalDataSourceProvider
import com.melike.mobile_summer_intern.data.remote.RemoteDataSourceProvider
import com.melike.mobile_summer_intern.presentation.model.GameResult
import javax.inject.Inject

class GamesAppRepository @Inject constructor(
    private val remoteDataSourceProvider: RemoteDataSourceProvider,
    private val localDataSourceProvider: LocalDataSourceProvider
) {

    suspend fun fetchGames() = remoteDataSourceProvider.fetchGames()

    suspend fun searchGames(query: String) = remoteDataSourceProvider.searchGames(query)

    suspend fun fetchGameDetail(id: Int) = remoteDataSourceProvider.fetchGameDetail(id)

    suspend fun addToWishlist(result: GameResult) = localDataSourceProvider.addWishList(result)

    suspend fun removeFromWishlist(id: Int) = localDataSourceProvider.removeFromWishlist(id)

    suspend fun isInWishList(id: Int?) = localDataSourceProvider.isInWishlist(id)
}
