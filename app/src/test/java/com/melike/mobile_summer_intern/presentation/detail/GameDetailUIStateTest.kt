package com.melike.mobile_summer_intern.presentation.detail

import android.content.Context
import android.util.Log
import com.melike.mobile_summer_intern.R
import com.melike.mobile_summer_intern.presentation.model.GameDetailResult
import com.melike.mobile_summer_intern.presentation.model.GenreModel
import com.melike.mobile_summer_intern.presentation.model.PublisherModel
import org.junit.Test
import org.mockito.Mockito.mock


class GameDetailUIStateTest {

    private val context: Context = mock(Context::class.java)


    @Test
    fun `given redditUrl is null when redditLinkVisibility called then should be return false`() {
        // Given
        val givenViewState = provideGameDetailUIState(
            gameDetailResult = provideGameDetailResult(redditUrl = "")
        )

        // When
        val result = givenViewState.redditLinkVisibility()

        //Then
        assert(!result)
    }

    @Test
    fun `given redditUrl is not null when redditLinkVisibility called then should be return true`() {
        // Given
        val givenViewState = provideGameDetailUIState(
            gameDetailResult = provideGameDetailResult(redditUrl = "url")
        )

        // When
        val result = givenViewState.redditLinkVisibility()

        //Then
        assert(result)
    }

    @Test
    fun `given metacritic is not null when metacriticVisibilty called then should be return true`() {
        // Given
        val givenViewState = provideGameDetailUIState(
            gameDetailResult = provideGameDetailResult(metacritic = 1)
        )

        // When
        val result = givenViewState.metacriticVisibility()

        //Then
        assert(result)
    }

    @Test
    fun `given metacritic is null when metacriticVisibility called then should be return false`() {
        // Given
        val givenViewState = provideGameDetailUIState(
            gameDetailResult = provideGameDetailResult(metacritic = 0)
        )

        // When
        val result = givenViewState.metacriticVisibility()

        //Then
        assert(!result)
    }

    @Test
    fun `given publishers is not empty when getInfoList called then should be return list which is includes publisher info model`() {
        // Given
        val givenViewState = provideGameDetailUIState(
            gameDetailResult = provideGameDetailResult(
                publishers = listOf(
                    PublisherModel(name = "publisher")
                )
            )
        )

        // When
        val result = givenViewState.getInfoList(context)

        //Then
        assert(result.any {
            it.title == context.getString(R.string.publishers)
        })
    }

    @Test
    fun `given publishers is null when publisherCheck called then should be return list which is not includes publisher info model`() {
        // Given
        val givenViewState = provideGameDetailUIState(
            gameDetailResult = provideGameDetailResult(
                publishers = listOf()
            )
        )

        // When
        val result = givenViewState.getInfoList(context)

        //Then
        assert(result.none {
            it.title == context.getString(R.string.publishers)
        })
    }

    @Test
    fun `given genres is not null when getInfoList called then should be return list which is includes genres info model`() {
        // Given
        val givenViewState = provideGameDetailUIState(
            gameDetailResult = provideGameDetailResult(
                genres = listOf(
                    GenreModel(name = "genres")
                )
            )
        )

        // When
        val result = givenViewState.getInfoList(context)

        //Then
        assert(!result.none {
            it.title == context.getString(R.string.genres)
        })
    }

    @Test
    fun `given genres is null when getInfoList called then should be return list which is not includes genres info model`() {
        // Given
        val givenViewState = provideGameDetailUIState(
            gameDetailResult = provideGameDetailResult(
                genres = listOf()
            )
        )

        // When
        val result = givenViewState.getInfoList(context)

        //Then
        assert(result.none {
            it.title == context.getString(R.string.genres)
        })
    }

    @Test
    fun `given releasedDate is not null when getInfoList called then should be return list which is includes released date info model`() {
        // Given
        val givenViewState = provideGameDetailUIState(
            gameDetailResult = provideGameDetailResult(released_date = "2015-05-18")
        )

        // When
        val result = givenViewState.getInfoList(context)

        //Then

        assert(result.any {
            it.title == context.getString(R.string.release_date)
        })
    }

    @Test
    fun `given releasedDate is null when getInfoList called then should be return list which is not includes released date info model`() {
        // Given
        val givenViewState = provideGameDetailUIState(
            gameDetailResult = provideGameDetailResult(released_date = "")
        )

        // When
        val result = givenViewState.getInfoList(context)

        //Then
        assert(result.none {
            it.title == context.getString(R.string.release_date)
        })
    }

    @Test
    fun `given playTime is null when getInfoList called then should be return list which is not includes play time info model`() {
        // Given
        val givenViewState = provideGameDetailUIState(
            gameDetailResult = provideGameDetailResult(play_time = 0)
        )

        // When
        val result = givenViewState.getInfoList(context)

        //Then
        assert(result.none {
            it.title == context.getString(R.string.play_time)
        })
    }

    @Test
    fun `given playTime is greater than 0 when getInfoList called then should be return list which is includes play time info model`() {
        // Given
        val givenViewState = provideGameDetailUIState(
            gameDetailResult = provideGameDetailResult(play_time = 10)
        )

        // When
        val result = givenViewState.getInfoList(context)

        //Then
        assert(result.any {
            it.title == context.getString(R.string.play_time)
        })
    }

    @Test
    fun `given metacrtic is lt 50 when metacriticBackground called then should be return bg_metacritic_high`() {
        // Given
        val givenViewState = provideGameDetailUIState(
            gameDetailResult = provideGameDetailResult(metacritic = 10)
        )

        // When
        val result = givenViewState.metacriticBackground()

        //Then
        assert(result == R.drawable.bg_metacritic_high)
    }

    @Test
    fun `given metacrtic is gt 75 when metacriticBackground called then should be return bg_metacritic_medium`() {
        // Given
        val givenViewState = provideGameDetailUIState(
            gameDetailResult = provideGameDetailResult(metacritic = 76)
        )

        // When
        val result = givenViewState.metacriticBackground()

        //Then
        assert(result == R.drawable.bg_metacritic_medium)
    }

    @Test
    fun `given metacrtic is gt 50 and lt 75  when metacriticBackground called then should be return bg_metacritic_low`() {
        // Given
        val givenViewState = provideGameDetailUIState(
            gameDetailResult = provideGameDetailResult(metacritic = 70)
        )

        // When
        val result = givenViewState.metacriticBackground()

        //Then
        assert(result == R.drawable.bg_metacritic_low)
    }

    @Test
    fun `given descriptionBxcStatus is COLLAPSED when getLineNumber called then should be 4`() {
        // Given
        val givenViewState = provideGameDetailUIState(
            descriptionBoxStatus = DescriptionBoxStatus.COLLAPSED,
            gameDetailResult = provideGameDetailResult()
        )

        // When
        val result = givenViewState.getLineNumber()

        //Then
        assert(result == 4)
    }

    @Test
    fun `given descriptionBoxStatus is EXTENDED when getLineNumber called then should be Int MAX_VALUE`() {
        // Given
        val givenViewState = provideGameDetailUIState(
            descriptionBoxStatus = DescriptionBoxStatus.EXTENDED,
            gameDetailResult = provideGameDetailResult()
        )

        // When
        val result = givenViewState.getLineNumber()

        //Then
        assert(result == Int.MAX_VALUE)
    }

    @Test
    fun `given descriptionBoxStatus is EXTENDED when changeDescriptionBoxStatus called then should be return COLLAPSED`() {
        // Given
        val givenViewState = provideGameDetailUIState(
            descriptionBoxStatus = DescriptionBoxStatus.EXTENDED,
            gameDetailResult = provideGameDetailResult()
        )

        // When
        val result = givenViewState.changeDescriptionBoxStatus()

        //Then
        assert(
            result == GameDetailUiState(
                gameDetailResult = provideGameDetailResult(),
                descriptionBoxStatus = DescriptionBoxStatus.COLLAPSED
            )
        )
    }

    @Test
    fun `given descriptionBoxStatus is COLLAPSED when changeDescriptionBoxStatus called then should be return EXTENDED`() {
        // Given
        val givenViewState = provideGameDetailUIState(
            descriptionBoxStatus = DescriptionBoxStatus.COLLAPSED,
            gameDetailResult = provideGameDetailResult()
        )

        // When
        val result = givenViewState.changeDescriptionBoxStatus()

        //Then
        assert(
            result == GameDetailUiState(
                gameDetailResult = provideGameDetailResult(),
                descriptionBoxStatus = DescriptionBoxStatus.EXTENDED
            )
        )
    }

    private fun provideGameDetailUIState(
        gameDetailResult: GameDetailResult = provideGameDetailResult(),
        descriptionBoxStatus: DescriptionBoxStatus = DescriptionBoxStatus.COLLAPSED
    ): GameDetailUiState = GameDetailUiState(
        gameDetailResult = gameDetailResult,
        descriptionBoxStatus = descriptionBoxStatus
    )

    private fun provideGameDetailResult(
        id: Int = 0,
        name: String = "",
        description: String = "",
        background_image: String = "",
        metacritic: Int = 0,
        publishers: List<PublisherModel> = listOf(),
        genres: List<GenreModel> = listOf(),
        play_time: Int = 0,
        released_date: String = "",
        redditUrl: String = "",
        website: String = ""
    ): GameDetailResult =
        GameDetailResult(
            id = id,
            name = name,
            description = description,
            background_image = background_image,
            metacritic = metacritic,
            publishers = publishers,
            genres = genres,
            play_time = play_time,
            released_date = released_date,
            redditUrl = redditUrl,
            website = website,
        )
}