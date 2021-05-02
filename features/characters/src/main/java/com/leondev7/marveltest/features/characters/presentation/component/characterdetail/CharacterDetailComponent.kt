package com.leondev7.marveltest.features.characters.presentation.component.characterdetail

import android.view.ViewGroup
import com.leondev7.marveltest.features.characters.domain.model.Character
import com.leondev7.marveltest.core.components.base.ComponentState
import com.leondev7.marveltest.core.components.base.MVIComponent
import com.leondev7.marveltest.features.characters.presentation.component.base.CharacterState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * Component to hold a detail of a Marvel Character
 */
open class CharacterDetailComponent(
    container: ViewGroup,
    uiScope : CoroutineScope,
    state : Flow<ComponentState>
) : MVIComponent {

    val uiView = initView(container)

    open fun initView(container: ViewGroup): CharacterDetailView {
        return CharacterDetailView(container)
    }

    override fun getContainerId() = uiView.containerId

    override fun show() {
        uiView.show()
    }

    override fun hide() {
        uiView.hide()
    }

    private fun loadData(character : Character){
        uiView.loadCharacterData(character)
    }


    init {
        uiScope.launch {
         state.collect {state->
             when (state) {
                 is CharacterState.DetailLoaded -> {
                     loadData(state.character)
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