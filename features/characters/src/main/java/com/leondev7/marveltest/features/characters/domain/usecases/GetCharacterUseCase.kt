package com.leondev7.marveltest.features.characters.domain.usecases

import com.leondev7.marveltest.core.usecases.UseCaseResult
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
    ): Flow<UseCaseResult> {
        return charactersRepository.getCharacterDetail(
            characterId = characterId,
        ).map { character ->
            UseCaseResult.Success(character) as UseCaseResult
        }.catch { error ->
            emit(UseCaseResult.Failure(error = error))
        }
    }


}