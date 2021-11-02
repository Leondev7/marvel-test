package com.leondev7.marveltest.features.characters.presentation.entity

import com.leondev7.marveltest.features.characters.domain.entity.CharacterDomainEntity

fun CharacterDomainEntity.toUiModel(): CharacterUiEntity {
    return CharacterUiEntity(
        this.id,
        this.name,
        this.description,
        this.thumbnail
    )
}