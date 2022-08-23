package com.melike.mobile_summer_intern.data.paging

import androidx.paging.PagingSource
import com.bumptech.glide.load.HttpException
import com.melike.mobile_summer_intern.common.util.Constants
import com.melike.mobile_summer_intern.data.model.ResultResponse
import com.melike.mobile_summer_intern.data.network.GamesService
import java.io.IOException
import javax.inject.Inject

class GameResponsePagingSource @Inject constructor(
    private val gamesService: GamesService,
    private val query: String?
) :
    PagingSource<Int, ResultResponse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultResponse> {
        val pageIndex = params.key ?: 1
        lateinit var response: MutableList<ResultResponse>
        return try {
            query?.let {
                response = gamesService.searchGames(
                    apiKey = Constants.API_KEY,
                    page = pageIndex,
                    query = query
                )?.results as MutableList<ResultResponse>

            } ?: run {
                response = gamesService.fetchGames(
                    apiKey = Constants.API_KEY,
                    page = pageIndex,
                )?.results as MutableList<ResultResponse>
            }
            val prevKey = if (pageIndex == 1) null else pageIndex - 1
            LoadResult.Page(data = response, prevKey = prevKey, nextKey = pageIndex + 1)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: retrofit2.HttpException) {
            LoadResult.Error(exception)
        }
    }
}