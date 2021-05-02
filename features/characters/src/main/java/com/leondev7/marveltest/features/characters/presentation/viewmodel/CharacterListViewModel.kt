package com.leondev7.marveltest.features.characters.presentation.viewmodel

import androidx.lifecycle.*
import com.leondev7.marveltest.features.characters.domain.model.Character
import com.leondev7.marveltest.features.characters.domain.repository.ICharactersRepository
import com.leondev7.marveltest.features.characters.presentation.component.base.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

const val PAGE_INIT_ELEMENTS = 0
const val PAGE_MAX_ELEMENTS = 10

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: ICharactersRepository

) : ViewModel() {

    private val _screenState = MutableStateFlow<ScreenState>(ScreenState.Loading)
    val screenState = _screenState.asStateFlow()

    init {
      getCharacters(PAGE_INIT_ELEMENTS)
    }

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
                if(screenState.value is ScreenState.ListLoaded){
                    val characters :ArrayList<Character> =  (screenState.value as ScreenState.ListLoaded).characterList as ArrayList<Character>
                    characters.addAll(newCharacters)
                    _screenState.emit(ScreenState.ListLoaded(characters))
                }else{
                    _screenState.emit(ScreenState.ListLoaded(newCharacters))

                }
        }


        }
    }

    fun getCharactersFromScratch(){
        runBlocking {_screenState.emit(ScreenState.Loading)  }
        getCharacters(0)
    }


}

