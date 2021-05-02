package com.leondev7.marveltest.features.characters.presentation.component.base

import com.leondev7.marveltest.features.characters.domain.model.Character

/**
 * Interaction events related to the list
 */
interface ListInteractionEvents {

    fun intentSwipe()

    fun intentEndReached( numberOfItems : Int)

    fun onCharacterClicked(character : Character)

}