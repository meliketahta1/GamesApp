package com.melike.mobile_summer_intern.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.melike.mobile_summer_intern.presentation.model.GameResult

@Dao
interface GameDao {
    @Insert
    suspend fun addToWishlist(gameResult: GameResult)

    @Query("DELETE FROM gameresult WHERE id = :id")
    suspend fun removeFromWishList(id: Int)

    @Query("SELECT EXISTS(SELECT * FROM GameResult WHERE id = :id)")
    suspend fun isInWishList(id: Int?): Boolean
}