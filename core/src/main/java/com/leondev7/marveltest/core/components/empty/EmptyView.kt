package com.leondev7.marveltest.core.components.empty

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.leondev7.marveltest.core.R
import com.leondev7.marveltest.core.components.base.UIView

class EmptyView(container: ViewGroup) : UIView(container) {

    val view: View =
        LayoutInflater.from(container.context).inflate(R.layout.layout_empty, container, true)
            .findViewById(R.id.empty_container)

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
