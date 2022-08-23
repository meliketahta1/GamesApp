package com.melike.mobile_summer_intern.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.melike.mobile_summer_intern.presentation.model.GameResult

@Database(entities = [GameResult::class], version = 1)
abstract class GameDatabase : RoomDatabase() {
    abstract fun gameDao(): GameDao
}