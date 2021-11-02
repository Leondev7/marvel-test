package com.leondev7.marveltest.features.characters.presentation.component.base

import com.leondev7.marveltest.features.characters.domain.entity.CharacterDomainEntity

/**
 * Interaction events related to the list
 */
interface ListInteractionEvents {

    fun intentSwipe()

    fun intentEndReached(limit : Int, numberOfItems : Int)

    fun onCharacterClicked(character : CharacterDomainEntity)

}