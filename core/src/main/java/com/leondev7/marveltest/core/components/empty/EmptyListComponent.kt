package com.leondev7.marveltest.core.components.empty

import android.view.ViewGroup
import com.leondev7.marveltest.core.components.base.ComponentState
import com.leondev7.marveltest.core.components.base.MVIComponent
import com.leondev7.marveltest.core.components.base.ScreenState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

open class EmptyListComponent(
    container: ViewGroup,
    uiScope : CoroutineScope,
    state : Flow<ComponentState>
) : MVIComponent {

    val uiView = initView(container)

    open fun initView(container: ViewGroup): EmptyView {
        return EmptyView(container)
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
                    is ScreenState.Empty -> {
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