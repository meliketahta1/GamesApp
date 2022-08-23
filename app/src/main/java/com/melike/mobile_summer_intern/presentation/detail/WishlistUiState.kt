package com.melike.mobile_summer_intern.presentation.detail

import com.melike.mobile_summer_intern.R

class WishlistUiState(private val isInWishlist: Boolean) {

    fun getWishlistDrawable(): Int {
        return if (isInWishlist) {
            R.drawable.ic_added_wishlist
        } else {
            R.drawable.ic_not_added_wishlist
        }
    }

    fun wishListStatus(): Boolean {
        return isInWishlist
    }
}