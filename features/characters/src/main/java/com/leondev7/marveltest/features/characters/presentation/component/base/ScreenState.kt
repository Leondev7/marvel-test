package com.leondev7.marveltest.features.characters.presentation.component.base

import com.leondev7.marveltest.features.characters.domain.model.Character


sealed class ScreenState : ComponentState {
    object Loading : ScreenState()
    class ListLoaded(val characterList : List<Character>) : ScreenState()
    class DetailLoaded(val character : Character) : ScreenState()
    class Error(val error : String) : ScreenState()
    object Empty : ScreenState()
}