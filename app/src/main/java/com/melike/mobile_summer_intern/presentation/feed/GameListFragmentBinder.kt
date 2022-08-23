package com.melike.mobile_summer_intern.presentation.feed

import androidx.core.view.isVisible
import androidx.paging.LoadState
import com.melike.mobile_summer_intern.databinding.FragmentGamesListBinding

fun FragmentGamesListBinding.bind(adapterState: LoadState) {
    this.pbGameList.isVisible = adapterState is LoadState.Loading
    this.rvGamesList.isVisible = adapterState !is LoadState.Loading
    this.rvGamesList.isVisible = adapterState !is LoadState.Loading
    this.tvNoGame.isVisible = adapterState is LoadState.Error
}