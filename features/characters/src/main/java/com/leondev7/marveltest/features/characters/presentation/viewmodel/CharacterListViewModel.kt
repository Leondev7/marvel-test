package com.leondev7.marveltest.features.characters.presentation.viewmodel

import androidx.lifecycle.*
import com.leondev7.marveltest.core.components.base.ComponentState
import com.leondev7.marveltest.features.characters.domain.model.Character
import com.leondev7.marveltest.features.characters.domain.repository.ICharactersRepository
import com.leondev7.marveltest.core.components.base.ScreenState
import com.leondev7.marveltest.features.characters.presentation.component.base.CharacterState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

const val PAGE_INIT_ELEMENTS = 0
const val PAGE_MAX_ELEMENTS = 10

/**
 * List view model to hold logic related to the CharacterListFragment
 */
@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: ICharactersRepository

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
     * Gets the characters from the repo.
     * If the previous state was [CharacterState.ListLoaded] it adds the new characters to the
     * previous state and emits the state
     * In case of error, retries 3 times and then emits an error
     * @param offset the number of characters to skip
     */
    fun getCharacters(offset : Int){
        viewModelScope.launch {
            var currentDelay = 1000L
            val delayFactor = 2
            flow{
                emit(repository.getCharacters(PAGE_MAX_ELEMENTS,offset))
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

        }.collect { newCharacters->
                if(screenState.value is CharacterState.ListLoaded){
                    val characters :ArrayList<Character> =  (screenState.value as CharacterState.ListLoaded).characterList as ArrayList<Character>
                    characters.addAll(newCharacters)
                    _screenState.emit(CharacterState.ListLoaded(characters))
                }else{
                    _screenState.emit(CharacterState.ListLoaded(newCharacters))

                }
        }


        }
    }

    /**
     * Helper method to get all the starting characters
     */
    fun getCharactersFromScratch(){
        runBlocking {_screenState.emit(ScreenState.Loading)  }
        getCharacters(PAGE_INIT_ELEMENTS)
    }


}

