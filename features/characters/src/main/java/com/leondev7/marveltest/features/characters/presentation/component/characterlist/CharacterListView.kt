package com.leondev7.marveltest.features.characters.presentation.component.characterlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.leondev7.marveltest.features.characters.R
import com.leondev7.marveltest.features.characters.domain.model.Character

import com.leondev7.marveltest.features.characters.presentation.component.base.ListInteractionEvents
import com.leondev7.marveltest.features.characters.presentation.component.base.UIView
import com.leondev7.marveltest.features.characters.presentation.component.characterlist.adapter.CharacterAdapter

enum class ListState{
    LOADING,LOADED
}

const val SPAN_COUNT = 1
class CharacterListView(container: ViewGroup, listInteractionEvents: ListInteractionEvents) :
    UIView(container) {

    private var state = ListState.LOADED

    private val view: View =
        LayoutInflater.from(container.context).inflate(R.layout.layout_character_list, container, true)
            .findViewById(R.id.list_container)

    override val containerId: Int = view.id

    private val adapter = CharacterAdapter{ character->
        listInteractionEvents.onCharacterClicked(character)
    }
    init {
        view.findViewById<SwipeRefreshLayout>(R.id.swipe_refresh).setOnRefreshListener {
            listInteractionEvents.intentSwipe()
        }
        val recyclerView = view.findViewById<RecyclerView>(R.id.characters_list)
        recyclerView.layoutManager  = GridLayoutManager(container.context, SPAN_COUNT)
        recyclerView.adapter = adapter
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE && state == ListState.LOADED) {
                    submitState(ListState.LOADING)
                    val totalItemCount: Int = recyclerView.layoutManager!!.itemCount
                    listInteractionEvents.intentEndReached(totalItemCount)
                }
            }
        })

    }

    private fun submitState(listState: ListState){
        state = listState
    }

    override fun show() {
        view.visibility = View.VISIBLE
    }

    override fun hide() {
        view.visibility = View.GONE
    }


    fun updateCharacters(characters: List<Character>) {
        view.findViewById<SwipeRefreshLayout>(R.id.swipe_refresh).isRefreshing = false
        adapter.data = characters
        submitState(ListState.LOADED)
    }


    override fun getViewState(): Bundle? {
        return null
    }

}