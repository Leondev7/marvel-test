package com.leondev7.marveltest.features.characters.domain.usecases

import com.leondev7.marveltest.core.usecases.UseCaseResult
import com.leondev7.marveltest.features.characters.domain.entity.CharacterDomainEntity
import com.leondev7.marveltest.features.characters.domain.error.CharacterError
import com.leondev7.marveltest.features.characters.domain.repository.ICharactersRepository
import kotlinx.coroutines.flow.*

/**
 * Gets a single character
 * @param charactersRepository The repository of the characters
 */
class GetCharacterUseCase(
    private val charactersRepository: ICharactersRepository,
) {
    /**
     * @param characterId The current character id
     * @return a [Flow] containing a [UseCaseResult]
     * [Character] if it's ok
     * [Throwable] if it's ko
     */
    suspend operator fun invoke(
        characterId : Long,
    ): Flow<UseCaseResult<CharacterDomainEntity>> {
        return charactersRepository.getCharacterDetail(
            characterId = characterId,
        ).map { character ->
            if(character != null){
                UseCaseResult.Success(character) as UseCaseResult<CharacterDomainEntity>
            }else{
                throw CharacterError.NotFound
            }
        }.catch { error ->
            emit(UseCaseResult.Failure(exception = error))
        }
    }


}