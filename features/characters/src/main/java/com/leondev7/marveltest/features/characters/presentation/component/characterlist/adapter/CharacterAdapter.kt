package com.leondev7.marveltest.features.characters.presentation.component.characterlist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.leondev7.marveltest.core.extensions.getImageByUrlCenterCrop
import com.leondev7.marveltest.features.characters.R
import com.leondev7.marveltest.features.characters.domain.model.Character
import kotlin.properties.Delegates


internal enum class ItemView(val type: Int) {
    CHARACTER(type = 0),
    LOADING(type = 1);

    companion object {
        fun valueOf(type: Int): ItemView = values().first { it.type == type }
    }
}


class CharacterAdapter(data: List<Character> = emptyList(), private val onCharacterClicked: (Character) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var data by Delegates.observable(data) { _, _, _ -> notifyDataSetChanged() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (ItemView.valueOf(viewType)) {
            ItemView.CHARACTER -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_character_view, parent, false)
                CharacterViewHolder(view) { character ->
                    this.onCharacterClicked(data[character])
                }
            }
            ItemView.LOADING -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_loading, parent, false)
                LoadingViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (ItemView.valueOf(getItemViewType(position))) {
            ItemView.CHARACTER -> {
                val character = data[position]
                val characterViewHolder: CharacterViewHolder = holder as CharacterViewHolder
                characterViewHolder.txtName.text = character.name
                characterViewHolder.imgThumbnail.getImageByUrlCenterCrop(character.thumbnail)
            }
            ItemView.LOADING -> {
                val loadingViewHolder = holder as LoadingViewHolder
                loadingViewHolder.progressBar.visibility = View.VISIBLE
            }
        }
    }



    override fun getItemCount() = data.size

    inner class CharacterViewHolder(view: View, onItemClicked: (Int) -> Unit)  : RecyclerView.ViewHolder(view) {
        init {
            itemView.setOnClickListener {
                onItemClicked(adapterPosition)
            }
        }
        val txtName: TextView = view.findViewById(R.id.txt_name)
        val imgThumbnail: ImageView = view.findViewById(R.id.img_thumbnail)


    }


    class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val progressBar = itemView.findViewById<View>(R.id.progress_load) as ProgressBar

    }

    override fun getItemViewType(position: Int): Int {
        return if (position == data.size - 1)  ItemView.LOADING.ordinal else   ItemView.CHARACTER.ordinal
    }


}