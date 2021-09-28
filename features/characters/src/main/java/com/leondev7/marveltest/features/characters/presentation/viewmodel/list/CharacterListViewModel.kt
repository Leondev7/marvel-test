package com.leondev7.marveltest.features.characters.presentation.viewmodel.list

import androidx.lifecycle.*
import com.leondev7.marveltest.core.components.base.ComponentState
import com.leondev7.marveltest.features.characters.domain.entity.CharacterDomainEntity
import com.leondev7.marveltest.features.characters.domain.repository.ICharactersRepository
import com.leondev7.marveltest.core.components.base.ScreenState
import com.leondev7.marveltest.core.usecases.UseCaseResult
import com.leondev7.marveltest.features.characters.domain.usecases.GetCharacterListUseCase
import com.leondev7.marveltest.features.characters.presentation.component.base.CharacterState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

const val PAGE_INIT_ELEMENTS = 0
const val PAGE_MAX_ELEMENTS = 10

/**
 * List view model to hold logic related to the CharacterListFragment
 */
class CharacterListViewModel constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getCharacterListUseCase: GetCharacterListUseCase,

) : ViewModel() {

    /**
     * The state of the screen
     */
    private val _screenState = MutableStateFlow<ComponentState>(ScreenState.Loading)
    val screenState = _screenState.asStateFlow()

    init {
      getCharactersFromScratch()
    }

    /**
     * Gets a list of characters
     * If the previous state was [CharacterState.ListLoaded] it adds the new characters to the
     * previous state and emits the state
     * In case of error, retries 3 times and then emits an error
     * @param offset the number of characters to skip
     * @param limit the maximum number of characters per page
     */
    fun getCharacterList(limit : Int, offset : Int){
        viewModelScope.launch {
            _screenState.emit(ScreenState.Loading)
            getCharacterListUseCase(
                limit = limit, offset = offset
            ).collect { result ->
                when (result) {
                    is UseCaseResult.Success<*>-> {
                        val characters =   result.content as List<CharacterDomainEntity>
                        _screenState.emit(CharacterState.ListLoaded(characters))
                    }
                    is UseCaseResult.Failure -> {
                        _screenState.emit(ScreenState.Error(result.error.message.toString()))
                    }
                }
            }
        }
    }

    /**
     * Helper method to get all the starting characters
     */
    fun getCharactersFromScratch(){
        getCharacterList(PAGE_MAX_ELEMENTS, PAGE_INIT_ELEMENTS)
    }


}

