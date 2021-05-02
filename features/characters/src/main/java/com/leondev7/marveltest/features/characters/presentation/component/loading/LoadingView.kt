package com.leondev7.marveltest.features.characters.presentation.component.loading

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.leondev7.marveltest.features.characters.R
import com.leondev7.marveltest.features.characters.presentation.component.base.UIView

class LoadingView(container: ViewGroup) : UIView(container) {

    private val view: View =
        LayoutInflater.from(container.context).inflate(R.layout.layout_loading, container, true)
            .findViewById(R.id.loading_container)

    override val containerId: Int = view.id

    init {
        view.visibility = View.GONE
    }
    override fun show() {
        view.visibility = View.VISIBLE
    }

    override fun hide() {
        view.visibility = View.GONE
    }


    override fun getViewState(): Bundle? {
        return null
    }
}
