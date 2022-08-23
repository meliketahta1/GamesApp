package com.melike.mobile_summer_intern.data.network

import com.melike.mobile_summer_intern.common.util.Constants
import com.melike.mobile_summer_intern.data.model.GameDetailResponse
import com.melike.mobile_summer_intern.data.model.GamesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GamesService {
    @GET("api/games")
    suspend fun fetchGames(
        @Query("key") apiKey: String = Constants.API_KEY,
        @Query("page") page: Int,
    ): GamesResponse?

    @GET("api/games")
    suspend fun searchGames(
        @Query("key") apiKey: String,
        @Query("page") page: Int,
        @Query("search") query: String,
    ): GamesResponse?

    @GET("api/games/{id}")
    suspend fun fetchGameDetail(
        @Path("id") id: Int,
        @Query("key") apiKey: String,
    ): GameDetailResponse?
}