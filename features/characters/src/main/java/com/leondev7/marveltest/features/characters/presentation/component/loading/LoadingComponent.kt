package com.leondev7.marveltest.features.characters.presentation.component.loading

import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import com.leondev7.marveltest.features.characters.presentation.component.base.ComponentState
import com.leondev7.marveltest.features.characters.presentation.component.base.MVIComponent
import com.leondev7.marveltest.features.characters.presentation.component.base.ScreenState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


open class LoadingComponent(
    container: ViewGroup,
    uiScope : CoroutineScope,
    state : Flow<ComponentState>
) : MVIComponent {

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val uiView = initView(container)

    open fun initView(container: ViewGroup): LoadingView {
        return LoadingView(container)
    }


    override fun getContainerId() = uiView.containerId

    override fun show() {
        uiView.show()
    }

    override fun hide() {
        uiView.hide()
    }

    init {
        uiScope.launch {
            state.collect {state->
                when (state) {
                    is ScreenState.Loading -> {
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