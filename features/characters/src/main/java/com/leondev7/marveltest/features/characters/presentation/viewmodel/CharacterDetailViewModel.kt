package com.leondev7.marveltest.features.characters.presentation.viewmodel

import androidx.lifecycle.*
import com.leondev7.marveltest.core.components.base.ComponentState
import com.leondev7.marveltest.features.characters.domain.repository.ICharactersRepository
import com.leondev7.marveltest.core.components.base.ScreenState
import com.leondev7.marveltest.features.characters.presentation.component.base.ListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: ICharactersRepository

) : ViewModel() {

    private val _characterId = MutableStateFlow(0L)
    val characterId = _characterId

    private val _screenState = MutableStateFlow<ComponentState>(ScreenState.Loading)
    val screenState = _screenState.asStateFlow()

    init {
        viewModelScope.launch {
            characterId.collect {
                if(it!=0L){
                    getCharacterDetail()
                }
            }
        }

    }
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
                _screenState.emit(ListState.DetailLoaded(character))

        }
        }
    }


}

