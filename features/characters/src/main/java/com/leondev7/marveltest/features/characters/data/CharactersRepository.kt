package com.leondev7.marveltest.features.characters.data

import com.leondev7.marveltest.core.network.MarvelApi
import com.leondev7.marveltest.features.characters.domain.repository.ICharactersRepository
import com.leondev7.marveltest.features.characters.domain.model.Character
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Characters Repository implementation to retrieve characters, in this case from an API
 */
@Singleton
class CharactersRepository @Inject constructor(private val marvelApi: MarvelApi) :
    ICharactersRepository {

    /**
     * Gets a list of characters from the [MarvelApi] and transforms them into [Character] list
     * @param limit the max number of characters that the API returns
     * @param offset the number of characters to skip
     * @return The list of characters
     */
    override suspend fun getCharacters(limit: Int, offset: Int) : List<Character> {
        return marvelApi.getMarvelCharacters(limit,offset).data.results.map { character ->
            Character(
                character.id,
                character.name,
                character.description,
                "${character.thumbnail.path}.${character.thumbnail.extension}"
            )
        }
    }

    /**
     * Gets a character detail from the [MarvelApi] and transforms it to [Character]
     * @param characterId The id of the character to retrieve
     * @return A single character
     */
    override suspend fun getCharacterDetail(characterId: Long): Character {
        return marvelApi.getCharacterDetail(characterId).data.results.map { character ->
            Character(
                character.id,
                character.name,
                character.description,
                "${character.thumbnail.path}.${character.thumbnail.extension}"
            )
        }.first()
    }
}