package com.melike.mobile_summer_intern.presentation.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GameResult(
    @PrimaryKey
    val id:Int,
    val name:String,
    val background_image:String,
)