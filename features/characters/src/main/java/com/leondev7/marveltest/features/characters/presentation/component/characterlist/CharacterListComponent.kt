package com.leondev7.marveltest.features.characters.presentation.component.characterlist

import android.view.ViewGroup
import com.leondev7.marveltest.features.characters.domain.model.Character
import com.leondev7.marveltest.core.components.base.*
import com.leondev7.marveltest.features.characters.presentation.component.base.ListInteractionEvents
import com.leondev7.marveltest.features.characters.presentation.component.base.ListState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

open class CharacterListComponent(
    container: ViewGroup,
    uiScope : CoroutineScope,
    state : Flow<ComponentState>,
    listInteractionEvents: ListInteractionEvents,
) : MVIComponent {

    val uiView = initView(container,listInteractionEvents)

    open fun initView(container: ViewGroup,listInteractionEvents: ListInteractionEvents): CharacterListView {
        return CharacterListView(container,listInteractionEvents)
    }

    override fun getContainerId() = uiView.containerId

    override fun show() {
        uiView.show()
    }

    override fun hide() {
        uiView.hide()
    }

    private fun updateList(characters : List<Character>){
        uiView.updateCharacters(characters)
    }


    init {
        uiScope.launch {
            state.collect {state->
                when (state) {
                    is ListState.ListLoaded -> {
                        updateList(state.characterList)
                        show()
                    }
                    else -> {
                        hide()
                    }
                }
            }

        }
    }
}