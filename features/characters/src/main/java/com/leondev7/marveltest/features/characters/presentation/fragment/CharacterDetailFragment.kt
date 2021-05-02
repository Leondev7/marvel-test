package com.leondev7.marveltest.features.characters.presentation.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.leondev7.marveltest.features.characters.R
import com.leondev7.marveltest.features.characters.presentation.component.base.UserInteractionEvents
import com.leondev7.marveltest.features.characters.presentation.component.characterdetail.CharacterDetailComponent
import com.leondev7.marveltest.features.characters.presentation.component.empty.EmptyListComponent
import com.leondev7.marveltest.features.characters.presentation.component.error.ErrorComponent
import com.leondev7.marveltest.features.characters.presentation.component.loading.LoadingComponent
import com.leondev7.marveltest.features.characters.presentation.viewmodel.CharacterDetailViewModel
import dagger.hilt.android.AndroidEntryPoint


/**
 * A fragment representing a list of Items.
 */
@AndroidEntryPoint
class CharacterDetailFragment : Fragment(), UserInteractionEvents {

    lateinit var loadingComponent: LoadingComponent
    lateinit var errorComponent: ErrorComponent
    lateinit var emptyListComponent: EmptyListComponent
    lateinit var characterDetailComponent: CharacterDetailComponent


    private val detailViewModel: CharacterDetailViewModel by activityViewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_character_detail, container, false)
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
                detailViewModel.screenState
        )
        errorComponent = ErrorComponent(
                rootViewContainer,
                lifecycleScope,
                detailViewModel.screenState
        ){
            intentTapRetry()
        }
        emptyListComponent = EmptyListComponent(
                rootViewContainer,
                lifecycleScope,
                detailViewModel.screenState
        )

        characterDetailComponent = CharacterDetailComponent(
                rootViewContainer,lifecycleScope,
                detailViewModel.screenState
        )

    }

    override fun intentTapRetry() {
        detailViewModel.getCharacterDetail()
    }


}