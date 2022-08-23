package com.melike.mobile_summer_intern.presentation.detail

import org.junit.Test
import com.melike.mobile_summer_intern.R

class WishlistUiStateTest{

    @Test
    fun `given isInWishlist is true when getWishListDrawable called then should be return ic_added_wishlist`() {
        // Given
        val givenViewState = WishlistUiState(isInWishlist = true)

        // When
        val result = givenViewState.getWishlistDrawable()

        //Then
        assert(result==R.drawable.ic_added_wishlist)
    }

    @Test
    fun `given isInWishlist is false when getWishListDrawable called then should be return ic_not_added_wishlist`() {
        // Given
        val givenViewState = WishlistUiState(isInWishlist = false)

        // When
        val result = givenViewState.getWishlistDrawable()

        //Then
        assert(result==R.drawable.ic_not_added_wishlist)
    }
}