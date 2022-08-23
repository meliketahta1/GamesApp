package com.melike.mobile_summer_intern.common.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.melike.mobile_summer_intern.R
import com.melike.mobile_summer_intern.common.util.Constants.Companion.months

fun ImageView.loadImage(url: String) {
    Glide.with(this)
        .load(url)
        .placeholder(R.drawable.bg_trendgames_vertical)
        .into(this)
}

fun String.formatDate(): String {
    val dateParams = this.split("-")
    var formattedDate = ""
    formattedDate += months.get(dateParams.get(1))
    formattedDate += ", " + dateParams.get(2)
    formattedDate += ", " + dateParams.get(0)
    return formattedDate
}