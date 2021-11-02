package com.leondev7.marveltest.features.characters.domain.usecases

import com.leondev7.marveltest.core.usecases.UseCaseResult
import com.leondev7.marveltest.features.characters.domain.entity.CharacterDomainEntity
import com.leondev7.marveltest.features.characters.domain.repository.ICharactersRepository
import kotlinx.coroutines.flow.*

/**
 * Gets a list of Characters
 * @param charactersRepository The repository of the characters
 */
class GetCharacterListUseCase(
    private val charactersRepository: ICharactersRepository,
) {
    /**
     * @param limit Maximum number of characters per page
     * @param offset Current offset
     * @return a [Flow] containing a [UseCaseResult]
     * a [List] of [Character] if it's ok
     * [Throwable] if it's ko
     */
    suspend operator fun invoke(
        limit : Int, offset : Int
    ): Flow<UseCaseResult<Array<CharacterDomainEntity>>> {
        return charactersRepository.getCharacters(
            limit = limit,
            offset = offset
        ).map { characters ->
            UseCaseResult.Success(characters) as UseCaseResult<Array<CharacterDomainEntity>>
        }.catch { error ->
            emit(UseCaseResult.Failure(exception = error))
        }
    }


}