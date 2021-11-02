package com.leondev7.marveltest.features.characters.presentation.detail

import androidx.lifecycle.*
import com.leondev7.marveltest.core.components.base.ComponentState
import com.leondev7.marveltest.features.characters.domain.repository.ICharactersRepository
import com.leondev7.marveltest.core.components.base.ScreenState
import com.leondev7.marveltest.features.characters.domain.usecases.GetCharacterListUseCase
import com.leondev7.marveltest.features.characters.domain.usecases.GetCharacterUseCase
import com.leondev7.marveltest.features.characters.presentation.component.base.CharacterState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class CharacterDetailViewModel  constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getCharacterUseCase: GetCharacterUseCase
) : ViewModel() {

    /**
     * The current character ID
     */
    private val _characterId = MutableStateFlow(0L)
    val characterId = _characterId

    /**
     * The state of the screen
     */
    private val _screenState = MutableStateFlow<ComponentState>(ScreenState.Loading)
    val screenState = _screenState.asStateFlow()

    /**
     * Starts observing the character ID, in case it changes to a new one, starts searching for
     * the character
     */
    init {
        viewModelScope.launch {
            characterId.collect {
                if(it!=0L){
                    //getCharacterDetail()
                }
            }
        }

    }

    /*
    /**
     * Gets a character with the current ID from the repo.
     * In case of error, retries 3 times and then emits an error
     */
    fun getCharacterDetail(){
        viewModelScope.launch {
            _screenState.emit(ScreenState.Loading)
            var currentDelay = 1000L
            val delayFactor = 2
            flow{
                emit(repository.getCharacterDetail(characterId.value))
            }.flowOn(Dispatchers.IO
            ).retry(retries = 3) { cause ->
            delay(currentDelay)
            currentDelay = (currentDelay * delayFactor)
            cause.printStackTrace()
            return@retry true
        }.catch { error->
                if(error.message!=null){
                    _screenState.emit(ScreenState.Error(error.message!!))
                }else{
                    _screenState.emit(ScreenState.Error("Unknown error"))
                }

        }.collect { character->
                _screenState.emit(CharacterState.DetailLoaded(character))

        }
        }
    }


     */

}

