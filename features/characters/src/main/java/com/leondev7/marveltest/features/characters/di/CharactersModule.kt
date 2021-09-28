package com.leondev7.marveltest.features.characters.di

import com.leondev7.marveltest.features.characters.data.CharactersRepository
import com.leondev7.marveltest.features.characters.domain.repository.ICharactersRepository
import com.leondev7.marveltest.features.characters.domain.usecases.GetCharacterListUseCase
import com.leondev7.marveltest.features.characters.domain.usecases.GetCharacterUseCase
import com.leondev7.marveltest.features.characters.presentation.viewmodel.detail.CharacterDetailViewModel
import com.leondev7.marveltest.features.characters.presentation.viewmodel.CharacterListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Characters feature module
 */
val charactersModule = module {
    plus(listOf(charactersAndroidModule, charactersUseCaseModule, charactersRepositoryModule))
}

/**
 * Positions feature android module
 * This module is in charge of injecting Android related dependencies like ViewModels
 */
val charactersAndroidModule = module {
    viewModel { CharacterListViewModel(get(),get()) }
    viewModel { CharacterDetailViewModel(get(),get()) }
}

/**
 * Characters feature UseCase module
 * This module is in charge of injecting Use Cases of the feature
 */
val charactersUseCaseModule = module {
    factory { GetCharacterListUseCase(get()) }
    factory { GetCharacterUseCase(get()) }
}

/**
 * Characters feature Repository module
 * This module is in charge of injecting Characters Repository
 */
val charactersRepositoryModule = module {
    single<ICharactersRepository> { CharactersRepository(get()) }
}