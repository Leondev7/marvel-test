package com.leondev7.marveltest.features.characters.data

import com.leondev7.marveltest.core.network.responses.CharacterResponse
import com.leondev7.marveltest.features.characters.domain.entity.CharacterDomainEntity

fun CharacterResponse.toDomainModel(): CharacterDomainEntity {
    return CharacterDomainEntity(
        this.id,
        this.name,
        this.description,
        "${this.thumbnail.path}.${this.thumbnail.extension}"
    )
}