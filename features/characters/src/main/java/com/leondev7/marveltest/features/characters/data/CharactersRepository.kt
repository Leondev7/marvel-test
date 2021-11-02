package com.leondev7.marveltest.features.characters.data

import com.leondev7.marveltest.core.network.IMarvelApiClient
import com.leondev7.marveltest.features.characters.domain.entity.CharacterDomainEntity
import com.leondev7.marveltest.features.characters.domain.repository.ICharactersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Characters Repository implementation to retrieve characters, in this case from an API
 */
class CharactersRepository
constructor(
    private val apiClient: IMarvelApiClient
) : ICharactersRepository {

    /**
     * Gets a list of characters from the [MarvelApi] and transforms them into [CharacterDomainEntity] list
     * @param limit the max number of characters that the API returns
     * @param offset the number of characters to skip
     * @return The list of characters
     */
    override suspend fun getCharacters(limit: Int, offset: Int): Flow<Array<CharacterDomainEntity>> {
       return apiClient.getMarvelCharacters(
            limit = limit,
            offset = offset
        ).map { response ->
            response.data.results.map {
                it.toDomainModel()
            }.toTypedArray()
        }
    }

    /**
     * Gets a character detail from the [MarvelApi] and transforms it to [CharacterDomainEntity]
     * @param characterId The id of the character to retrieve
     * @return A single character
     */
    override suspend fun getCharacterDetail(characterId: Long): Flow<CharacterDomainEntity?> {
        return apiClient.getCharacterDetail(
            characterId = characterId,
        ).map { response ->
            response.data.results.map { it.toDomainModel() }.firstOrNull()
        }
    }
}