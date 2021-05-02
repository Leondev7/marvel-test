package com.leondev7.marveltest.features.characters.domain.repository

import com.leondev7.marveltest.features.characters.domain.model.Character

interface ICharactersRepository {

    suspend fun getCharacters(limit : Int, offset : Int) : List<Character>

    suspend fun getCharacterDetail(characterId : Long) : Character
}