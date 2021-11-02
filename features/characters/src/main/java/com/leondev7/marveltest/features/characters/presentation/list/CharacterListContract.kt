package com.leondev7.marveltest.features.characters.presentation.list

import com.leondev7.marveltest.core.mvi.UiEffect
import com.leondev7.marveltest.core.mvi.UiEvent
import com.leondev7.marveltest.core.mvi.UiState
import com.leondev7.marveltest.features.characters.presentation.entity.CharacterUiEntity

class CharacterListContract {
    sealed class Event : UiEvent {
        object Retry : Event()
        object Swipe : Event()
        class EndReached(val limit : Int, val offset : Int) : Event()
        class CharacterClicked(val character: CharacterUiEntity) : Event()
    }

    sealed class State : UiState {
        object Loading : State()
        object Updating : State()
        data class Success(val characters : List<CharacterUiEntity>) : State()
        data class Failure(val error : Throwable) : State()
        object Empty : State()

        fun isLoading() = this is Loading
        fun isUpdating() = this is Updating
        fun isLoaded() = this is Success
        fun isFailure() = this is Failure
        fun isEmpty() = this is Empty
    }

    sealed class Effect : UiEffect
}