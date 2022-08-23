package com.melike.mobile_summer_intern.data.local

import com.melike.mobile_summer_intern.presentation.model.GameResult

interface LocalDataSource {
    suspend fun addWishList(gameResult: GameResult)

    suspend fun removeFromWishlist(id: Int)

    suspend fun isInWishlist(id: Int?): Boolean
}