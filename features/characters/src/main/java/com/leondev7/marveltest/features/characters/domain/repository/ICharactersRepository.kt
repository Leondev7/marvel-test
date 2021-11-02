package com.leondev7.marveltest.features.characters.domain.repository

import com.leondev7.marveltest.features.characters.domain.entity.CharacterDomainEntity
import kotlinx.coroutines.flow.Flow

interface ICharactersRepository {

    suspend fun getCharacters(limit : Int, offset : Int) : Flow<Array<CharacterDomainEntity>>

    suspend fun getCharacterDetail(characterId : Long) : Flow<CharacterDomainEntity?>
}