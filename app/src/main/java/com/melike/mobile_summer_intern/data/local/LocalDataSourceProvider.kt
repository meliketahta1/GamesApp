package com.melike.mobile_summer_intern.data.local

import com.melike.mobile_summer_intern.presentation.model.GameResult
import javax.inject.Inject

class LocalDataSourceProvider @Inject constructor(
    private val dao: GameDao
) : LocalDataSource {

    override suspend fun addWishList(gameResult: GameResult) =
        dao.addToWishlist(gameResult)

    override suspend fun removeFromWishlist(id: Int) =
        dao.removeFromWishList(id)

    override suspend fun isInWishlist(id: Int?): Boolean =
        dao.isInWishList(id)
}