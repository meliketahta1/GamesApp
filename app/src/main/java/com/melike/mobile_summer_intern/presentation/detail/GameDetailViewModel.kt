package com.melike.mobile_summer_intern.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melike.mobile_summer_intern.common.ResultState
import com.melike.mobile_summer_intern.domailn.AddToWishlistUseCase
import com.melike.mobile_summer_intern.domailn.DeleteFromWishlistUseCase
import com.melike.mobile_summer_intern.domailn.FetchGameDetailUseCase
import com.melike.mobile_summer_intern.domailn.IsInWishListUseCase
import com.melike.mobile_summer_intern.presentation.model.GameDetailResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameDetailViewModel @Inject constructor(
    private val fetchGameDetailUseCase: FetchGameDetailUseCase,
    private val isInWishListUseCase: IsInWishListUseCase,
    private val addToWishlistUseCase: AddToWishlistUseCase,
    private val deleteFromWishlistUseCase: DeleteFromWishlistUseCase
) : ViewModel() {

    private val _gamesResultState = MutableStateFlow<GameDetailUiState?>(GameDetailUiState())
    val gamesResultState: MutableStateFlow<GameDetailUiState?> = _gamesResultState

    private val _gamesStatusState =
        MutableStateFlow<GameStatusUIState>(GameStatusUIState(GameDetailStatus.LOADING))
    val gamesStatusState: MutableStateFlow<GameStatusUIState> = _gamesStatusState

    private val _isInWishList = MutableStateFlow<WishlistUiState>(WishlistUiState(false))
    val isInWishList: MutableStateFlow<WishlistUiState> = _isInWishList

    private val dialogActionSender = Channel<Boolean>()
    val dialogActionReceiver = dialogActionSender.receiveAsFlow()


    fun initViewModel(id: Int) {
        fetchGameDetail(id)
        isInWishList(id)
    }

    private fun fetchGameDetail(id: Int) = viewModelScope.launch {
        fetchGameDetailUseCase.invoke(id).collect { state ->
            when (state) {
                is ResultState.Success -> {
                    _gamesResultState.value =
                        GameDetailUiState(state.data as GameDetailResult)
                    _gamesStatusState.value = GameStatusUIState(GameDetailStatus.SUCCESS)
                }
                is ResultState.Loading -> {
                    _gamesStatusState.value = GameStatusUIState(GameDetailStatus.LOADING)
                }
            }
        }
    }

    fun handleWishlistOperation(gameDetailResult: GameDetailResult) {
        if (isInWishList.value.wishListStatus()) {
            viewModelScope.launch { dialogActionSender.send(true) }
        } else {
            addToWishlist(gameDetailResult)
        }
    }


    private fun isInWishList(id: Int) = viewModelScope.launch {
        isInWishListUseCase.invoke(id).collect { resultState ->
            when (resultState) {
                is ResultState.Success -> {
                    _isInWishList.value = WishlistUiState(resultState.data as Boolean)
                }
            }
        }
    }

    private fun addToWishlist(gameDetailResult: GameDetailResult) = viewModelScope.launch {
        addToWishlistUseCase.invoke(gameDetailResult).collect { resultState ->
            when (resultState) {
                is ResultState.Success -> {
                    _isInWishList.value = WishlistUiState(true)
                }
            }
        }
    }

    fun deleteFromWishlist(id: Int) = viewModelScope.launch {
        deleteFromWishlistUseCase.invoke(id).collect { resultState ->
            _isInWishList.value = WishlistUiState(false)
        }
    }

    fun changeLineNumber() {
        _gamesResultState.value = _gamesResultState.value?.changeDescriptionBoxStatus()
    }
}