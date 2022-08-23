package com.melike.mobile_summer_intern.presentation.detail

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.melike.mobile_summer_intern.common.ResultState
import com.melike.mobile_summer_intern.domailn.AddToWishlistUseCase
import com.melike.mobile_summer_intern.domailn.DeleteFromWishlistUseCase
import com.melike.mobile_summer_intern.domailn.FetchGameDetailUseCase
import com.melike.mobile_summer_intern.domailn.IsInWishListUseCase
import com.melike.mobile_summer_intern.presentation.model.GameDetailResult
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class GameDetailViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var fetchGameDetailUseCase: FetchGameDetailUseCase

    @MockK
    lateinit var isInWishListUseCase: IsInWishListUseCase

    @MockK
    lateinit var addToWishlistUseCase: AddToWishlistUseCase

    @MockK
    lateinit var deleteFromWishlistUseCase: DeleteFromWishlistUseCase


    private lateinit var gameDetailViewModel: GameDetailViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        MockKAnnotations.init(this)
        gameDetailViewModel = GameDetailViewModel(
            fetchGameDetailUseCase,
            isInWishListUseCase,
            addToWishlistUseCase,
            deleteFromWishlistUseCase
        )

    }

    @Test
    fun `given loading response when initViewModel called then gameStatusState should be equals to LOADING`() {
        // Given
        every { fetchGameDetailUseCase.invoke(4) } answers { flow { emit(ResultState.Loading()) } }

        // When
        gameDetailViewModel.initViewModel(4)

        // Then
        Truth.assertThat(gameDetailViewModel.gamesStatusState.value)
            .isEqualTo(GameStatusUIState(GameDetailStatus.LOADING))
    }

    @Test
    fun `given loading response when initViewModel called then gameStatusState should not be equals to SUCCESS`() {
        // Given
        every { fetchGameDetailUseCase.invoke(4) } answers { flow { emit(ResultState.Loading()) } }

        // When
        gameDetailViewModel.initViewModel(4)

        // Then
        Truth.assertThat(gameDetailViewModel.gamesStatusState.value)
            .isNotEqualTo(GameStatusUIState(GameDetailStatus.SUCCESS))
    }

    @Test
    fun `given success response when initViewModel called then gameStatusState should be equals to SUCCESS`() {
        // Given
        every { fetchGameDetailUseCase.invoke(4) } answers {
            flow {
                emit(
                    ResultState.Success(
                        provideGameDetailResult()
                    )
                )
            }
        }

        // When
        gameDetailViewModel.initViewModel(4)

        // Then
        Truth.assertThat(gameDetailViewModel.gamesStatusState.value)
            .isEqualTo(GameStatusUIState(GameDetailStatus.SUCCESS))
    }

    @Test
    fun `given success response when initViewModel called then gameStatusState should not be equals to LOADING`() {
        // Given
        every { fetchGameDetailUseCase.invoke(4) } answers {
            flow {
                emit(
                    ResultState.Success(
                        provideGameDetailResult()
                    )
                )
            }
        }

        // When
        gameDetailViewModel.initViewModel(4)

        // Then
        Truth.assertThat(gameDetailViewModel.gamesStatusState.value)
            .isNotEqualTo(GameStatusUIState(GameDetailStatus.LOADING))
    }

    @Test
    fun `given success response when initViewModel called then gameDetailUIState should be equals to GameDetailUiState with success response`() {
        every { fetchGameDetailUseCase.invoke(4) } answers {
            flow {
                emit(
                    ResultState.Success(
                        provideGameDetailResult()
                    )
                )
            }
        }

        // When
        gameDetailViewModel.initViewModel(4)

        // Then
        Truth.assertThat(gameDetailViewModel.gamesResultState.value)
            .isEqualTo(GameDetailUiState(provideGameDetailResult()))
    }

    @Test
    fun `given error response when initViewModel called then gameDetailUIState should be equals to GameDetailUiState with initial state`() {
        every { fetchGameDetailUseCase.invoke(4) } answers {
            flow {
                emit(
                    ResultState.Error(
                        exception = "An exception"
                    )
                )
            }
        }

        // When
        gameDetailViewModel.initViewModel(4)

        // Then
        Truth.assertThat(gameDetailViewModel.gamesResultState.value)
            .isEqualTo(GameDetailUiState())
    }

    @Test
    fun `given isInWishList false when handleWishlistOperation called then should addToWishListUseCase trigger`() {
        // Given
        (gameDetailViewModel.isInWishList).value = WishlistUiState(false)

        // When
        gameDetailViewModel.handleWishlistOperation(provideGameDetailResult())

        //Then
        verify {
            addToWishlistUseCase.invoke(provideGameDetailResult())
        }
    }

    @Test
    fun `given isInWishList true when handleWishlistOperation called then dialogActionSender must be send true`() {
        // Given
        val state = WishlistUiState(true)
        gameDetailViewModel.isInWishList.value = state

        // When
        gameDetailViewModel.handleWishlistOperation(provideGameDetailResult())

        //Then
        runTest {
            Truth.assertThat(gameDetailViewModel.dialogActionReceiver.first()).isEqualTo(true)
        }
    }
}

private fun provideGameDetailResult() =
    GameDetailResult(
        id = 0,
        name = "name",
        description = "description",
        background_image = "background",
        metacritic = 0,
        publishers = listOf(),
        genres = listOf(),
        play_time = 0,
        released_date = "released_date",
        redditUrl = "redditUrl",
        website = "website"
    )