package com.melike.mobile_summer_intern.domailn.mapper

import com.melike.mobile_summer_intern.data.model.GenreResponse
import com.melike.mobile_summer_intern.presentation.model.GenreModel
import javax.inject.Inject

class GenreModelMapper @Inject constructor() {

    fun mapGenreResponse(genreResponse: List<GenreResponse>?): List<GenreModel> {
        val genreList = mutableListOf<GenreModel>()
        genreResponse?.forEach {
            genreList.add(GenreModel(name = it.name.orEmpty()))
        }
        return genreList
    }
}