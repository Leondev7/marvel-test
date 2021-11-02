package com.leondev7.marveltest.features.characters.presentation.list

import androidx.lifecycle.*
import com.leondev7.marveltest.features.characters.domain.entity.CharacterDomainEntity
import com.leondev7.marveltest.core.mvi.UiState
import com.leondev7.marveltest.core.usecases.UseCaseResult
import com.leondev7.marveltest.features.characters.domain.usecases.GetCharacterListUseCase
import com.leondev7.marveltest.features.characters.presentation.component.base.CharacterState
import com.leondev7.marveltest.features.characters.presentation.entity.CharacterUiEntity
import com.leondev7.marveltest.features.characters.presentation.entity.toUiModel
import com.leondev7.marveltest.features.characters.presentation.list.CharacterListContract.Event.*
import com.leondev7.marveltest.features.characters.presentation.list.CharacterListContract.State
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

const val PAGE_INIT_ELEMENTS = 0
const val PAGE_MAX_ELEMENTS = 10

/**
 * List view model to hold logic related to the CharacterListFragment
 */
class CharacterListViewModel
constructor(
    private val getCharacterListUseCase: GetCharacterListUseCase,
) : ViewModel() {

    private val _uiState = MutableLiveData<State>()
    val uiState: LiveData<State> = _uiState

    fun getCharactersFromScratch(){
        updateUiState(State.Loading)
        getCharacterList(
            PAGE_MAX_ELEMENTS,
            PAGE_INIT_ELEMENTS
        )
    }

    fun updateCharacters(limit: Int, offset: Int){
        updateUiState(State.Updating)
        getCharacterList(
            limit = limit,
            offset = offset
        )
    }

    /**
     * Gets a list of characters
     * If the previous state was [CharacterState.ListLoaded] it adds the new characters to the
     * previous state and emits the state
     * In case of error, retries 3 times and then emits an error
     * @param offset the number of characters to skip
     * @param limit the maximum number of characters per page
     */
    private fun getCharacterList(limit: Int, offset: Int) {
        viewModelScope.launch {
            handleListResult(getCharacterListUseCase(
                limit = limit,
                offset = offset,
            ).single())

        }
    }

    /**
     * Handles the [GetCharacterListUseCase] result
     * @param result An [UseCaseResult] containing error or success
     */
    private fun handleListResult(result: UseCaseResult<Array<CharacterDomainEntity>>) {
        when (result) {
            is UseCaseResult.Success<Array<CharacterDomainEntity>> -> {
                handleCharacters(characters = result.data.map { it.toUiModel() })
            }
            is UseCaseResult.Failure -> {
                updateUiState(State.Failure(result.exception))
            }
        }
    }

    /**
     * Updates The UI State
     */
    private fun updateUiState(newUiState: State) {
        _uiState.postValue(newUiState)
    }

    /**
     * Handles the [GetCharacterListUseCase] result
     */
    private fun handleCharacters(characters: List<CharacterUiEntity>) {
        if (characters.isNotEmpty()) {
            updateUiState(State.Success(characters))
        } else {
            updateUiState(State.Empty)
        }
    }

}

