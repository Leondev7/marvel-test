package com.leondev7.marveltest.features.characters.presentation.component.error

import android.view.ViewGroup
import com.leondev7.marveltest.features.characters.presentation.component.base.ComponentState
import com.leondev7.marveltest.features.characters.presentation.component.base.MVIComponent
import com.leondev7.marveltest.features.characters.presentation.component.base.ScreenState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

open class ErrorComponent(
    container: ViewGroup,
    uiScope : CoroutineScope,
    state : Flow<ComponentState>,
    retry: () -> Unit,
) : MVIComponent {

    val uiView = initView(container,retry)

    open fun initView(container: ViewGroup, event : () -> (Unit)): ErrorView {
        return ErrorView(container, event)
    }

    override fun getContainerId() = uiView.containerId

    override fun show() {
        uiView.show()
    }

    override fun hide() {
        uiView.hide()
    }

    private fun showError(error : String){
        uiView.showError(error)
    }


    init {
        uiScope.launch {
         state.collect {state->
             when (state) {
                 is ScreenState.Error -> {
                     showError(state.error)
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