package com.leondev7.marveltest.features.characters.presentation.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.leondev7.marveltest.features.characters.R
import com.leondev7.marveltest.features.characters.domain.entity.CharacterDomainEntity
import com.leondev7.marveltest.core.components.base.UserInteractionEvents
import com.leondev7.marveltest.features.characters.presentation.component.characterlist.CharacterListComponent
import com.leondev7.marveltest.core.components.empty.EmptyListComponent
import com.leondev7.marveltest.core.components.error.ErrorComponent
import com.leondev7.marveltest.core.components.loading.LoadingComponent
import com.leondev7.marveltest.features.characters.presentation.component.base.ListInteractionEvents
import com.leondev7.marveltest.features.characters.presentation.viewmodel.CharacterListViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A fragment representing a list of Items.
 */
@AndroidEntryPoint
class CharacterListFragment : Fragment(), UserInteractionEvents, ListInteractionEvents {

    lateinit var loadingComponent: LoadingComponent
    lateinit var errorComponent: ErrorComponent
    lateinit var emptyListComponent: EmptyListComponent
    lateinit var characterListComponent: CharacterListComponent


    private val charactersViewModel: CharacterListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_character_list, container, false)
        val root = view.findViewById<ConstraintLayout>(R.id.root)
        initComponents(root)
        return view
    }


    /**
     * Initialize all UI Components
     */
    @SuppressLint("CheckResult")
    private fun initComponents(rootViewContainer: ViewGroup) {
        loadingComponent = LoadingComponent(
            rootViewContainer,
            lifecycleScope,
            charactersViewModel.screenState
        )
        errorComponent = ErrorComponent(
            rootViewContainer,
            lifecycleScope,
            charactersViewModel.screenState
        ){
            intentTapRetry()
        }
        emptyListComponent = EmptyListComponent(
            rootViewContainer,
            lifecycleScope,
            charactersViewModel.screenState
        )
        characterListComponent = CharacterListComponent(
            rootViewContainer,lifecycleScope,
            charactersViewModel.screenState,
            this
        )
    }

    /**
     * Retry intent in case of error
     */
    override fun intentTapRetry() {
        charactersViewModel.getCharactersFromScratch()
    }

    /**
     * Reload intent in case of swipe
     */
    override fun intentSwipe() {
        charactersViewModel.getCharactersFromScratch()
    }

    /**
     * Load more characters in case of list end reached
     * @param numberOfItems the current number items to load as an offset
     */
    override fun intentEndReached(numberOfItems: Int) {
        charactersViewModel.getCharacters(numberOfItems)
    }

    /**
     * Character clicked event, navigate to detail
     * @param character the character clicked
     */
    override fun onCharacterClicked(character: CharacterDomainEntity) {
        findNavController(
        ).navigate(
            CharacterListFragmentDirections.
            actionCharactersFragmentToDetailFragment(characterId = character.id))
    }
}