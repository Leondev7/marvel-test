package com.leondev7.marveltest.features.characters.presentation.component.base

import com.leondev7.marveltest.core.components.base.ComponentState
import com.leondev7.marveltest.features.characters.domain.model.Character

sealed class ListState : ComponentState {
    class ListLoaded(val characterList : List<Character>) : ListState()
    class DetailLoaded(val character : Character) : ListState()
}