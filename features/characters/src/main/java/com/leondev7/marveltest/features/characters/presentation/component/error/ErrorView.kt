package com.leondev7.marveltest.features.characters.presentation.component.error

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.leondev7.marveltest.features.characters.R
import com.leondev7.marveltest.features.characters.presentation.component.base.UIView

class ErrorView(container: ViewGroup, retry: () -> Unit) :
    UIView(container) {
    private val view: View =
        LayoutInflater.from(container.context).inflate(R.layout.layout_error, container, true)
            .findViewById(R.id.error_container)

    override val containerId: Int = view.id

    init {
        view.findViewById<Button>(R.id.btn_retry)
            .setOnClickListener {
                  retry()
            }

    }

    override fun show() {
        view.visibility = View.VISIBLE
    }

    override fun hide() {
        view.visibility = View.GONE
    }

    fun showError(error : String){
        view.findViewById<TextView>(R.id.txt_error).text = error
    }

    override fun getViewState(): Bundle? {
        return null
    }
}