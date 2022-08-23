package com.melike.mobile_summer_intern.domailn.mapper

import com.melike.mobile_summer_intern.data.model.PublisherResponse
import com.melike.mobile_summer_intern.presentation.model.PublisherModel
import javax.inject.Inject

class PublisherModelMapper @Inject constructor() {
    fun mapPublisherModel(publisherResponse: List<PublisherResponse>?): List<PublisherModel> {
        val publisherList = mutableListOf<PublisherModel>()
        publisherResponse?.forEach {
            publisherList.add(PublisherModel(name = it.name.orEmpty()))
        }
        return publisherList
    }
}