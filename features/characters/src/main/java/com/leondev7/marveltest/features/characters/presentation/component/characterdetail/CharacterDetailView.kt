package com.leondev7.marveltest.features.characters.presentation.component.characterdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.leondev7.marveltest.core.extensions.getImageByUrlCenterCrop
import com.leondev7.marveltest.features.characters.R
import com.leondev7.marveltest.features.characters.domain.entity.CharacterDomainEntity
import com.leondev7.marveltest.core.components.base.UIView

/**
 * View related to [CharacterDetailComponent]
 */
class CharacterDetailView(container: ViewGroup) :
    UIView(container) {
    private val view: View =
        LayoutInflater.from(container.context).inflate(R.layout.layout_character_detail, container, true)
            .findViewById(R.id.detail_container)

    override val containerId: Int = view.id


    override fun show() {
        view.visibility = View.VISIBLE
    }

    override fun hide() {
        view.visibility = View.GONE
    }

    fun loadCharacterData(character : CharacterDomainEntity){
        val textName = view.findViewById<View>(R.id.character_name) as TextView
        val description = view.findViewById<View>(R.id.character_description) as TextView
        val image = view.findViewById<View>(R.id.dialog_character_image) as ImageView

        textName.text = character.name
        description.text = character.description
        image.getImageByUrlCenterCrop(character.thumbnail)
    }

    override fun getViewState(): Bundle? {
        return null
    }
}