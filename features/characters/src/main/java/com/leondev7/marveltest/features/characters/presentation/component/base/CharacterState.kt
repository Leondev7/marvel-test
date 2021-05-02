package com.leondev7.marveltest.features.characters.presentation.component.base

import com.leondev7.marveltest.core.components.base.ComponentState
import com.leondev7.marveltest.features.characters.domain.model.Character

/**
 * ScreenState related to the characters screens
 */
sealed class CharacterState : ComponentState {
    class ListLoaded(val characterList : List<Character>) : CharacterState()
    class DetailLoaded(val character : Character) : CharacterState()
}