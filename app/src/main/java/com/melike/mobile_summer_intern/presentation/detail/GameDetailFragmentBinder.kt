package com.melike.mobile_summer_intern.presentation.feed

import android.text.Html
import androidx.core.view.isVisible
import com.melike.mobile_summer_intern.common.util.loadImage
import com.melike.mobile_summer_intern.databinding.FragmentGameDetailBinding
import com.melike.mobile_summer_intern.presentation.detail.GameDetailUiState

fun FragmentGameDetailBinding.bindUIState(gameDetailUiState: GameDetailUiState) {
    this.tvDescription.maxLines = gameDetailUiState.getLineNumber()
    this.tvGameName.text = gameDetailUiState.gameDetailResult.name
    this.tvGoReddit.isVisible = gameDetailUiState.redditLinkVisibility()
    this.tvGoWebsite.isVisible = gameDetailUiState.websiteLinkVisibility()
    gameDetailUiState.gameDetailResult.background_image.let { image ->
        this.ivGamePoster.loadImage(
            image
        )
    }
    this.tvMetacritic.text = gameDetailUiState.gameDetailResult.metacritic.toString()
    this.tvMetacritic.isVisible = gameDetailUiState.metacriticVisibility()
    this.tvMetacritic.setBackgroundResource(gameDetailUiState.metacriticBackground())
    this.tvDescription.text =
        Html.fromHtml(gameDetailUiState.gameDetailResult.description).toString()
}