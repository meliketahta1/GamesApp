package com.melike.mobile_summer_intern.presentation.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.melike.mobile_summer_intern.domailn.FetchGamesUseCase
import com.melike.mobile_summer_intern.domailn.SearchGameUseCase
import com.melike.mobile_summer_intern.presentation.model.GameResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@HiltViewModel
class GamesFeedViewModel @Inject constructor(
    private val fetchGamesUseCase: FetchGamesUseCase,
    private val searchGameUseCase: SearchGameUseCase,
) : ViewModel() {

    private val _gamesResultState = MutableStateFlow<PagingData<GameResult>?>(null)
    val gamesResultState: MutableStateFlow<PagingData<GameResult>?> = _gamesResultState

    init {
        fetchResponse()
    }

    private fun fetchResponse() = viewModelScope.launch {
        fetchGamesUseCase.invoke().distinctUntilChanged().collect { state ->
            _gamesResultState.value = state
        }
    }

    fun searchGames(query: String) = viewModelScope.launch {
        searchGameUseCase.invoke(query).collect { state ->
            _gamesResultState.value = state

        }
    }
}