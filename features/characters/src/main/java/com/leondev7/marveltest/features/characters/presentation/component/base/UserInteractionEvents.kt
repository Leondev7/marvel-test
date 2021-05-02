package com.leondev7.marveltest.features.characters.presentation.component.base

import com.leondev7.marveltest.features.characters.domain.model.Character

/**
 * List of all events Views can emit
 */
interface UserInteractionEvents {

    fun intentTapRetry()


}

interface ListInteractionEvents {

    fun intentSwipe()

    fun intentEndReached( numberOfItems : Int)

    fun onCharacterClicked(character : Character)

}