package com.leondev7.marveltest.features.characters.presentation.component.base

import android.os.Bundle
import android.view.ViewGroup
import androidx.annotation.IdRes

abstract class UIView(open val container: ViewGroup) {
    /**
     * Get the XML id for the IUIView
     */
    @get:IdRes
    abstract val containerId: Int

    /**
     * Show the IUIView
     */
    abstract fun show()

    /**
     * Hide the IUIView
     */
    abstract fun hide()

    abstract fun getViewState(): Bundle?
}