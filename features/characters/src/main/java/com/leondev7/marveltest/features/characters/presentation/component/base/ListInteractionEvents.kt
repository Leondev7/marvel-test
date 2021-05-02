package com.leondev7.marveltest.features.characters.presentation.component.base

import com.leondev7.marveltest.features.characters.domain.model.Character

interface ListInteractionEvents {

    fun intentSwipe()

    fun intentEndReached( numberOfItems : Int)

    fun onCharacterClicked(character : Character)

}