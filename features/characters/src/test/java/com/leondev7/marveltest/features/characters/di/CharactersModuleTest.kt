package com.leondev7.marveltest.features.characters.di

import com.leondev7.marveltest.features.characters.domain.repository.ICharactersRepository
import com.leondev7.marveltest.features.characters.domain.usecases.GetCharacterListUseCase
import com.leondev7.marveltest.features.characters.domain.usecases.GetCharacterUseCase
import com.leondev7.marveltest.features.characters.presentation.list.CharacterListViewModel
import io.mockk.mockkClass
import org.junit.Test
import org.junit.Rule
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.get
import org.koin.test.mock.MockProviderRule
import org.koin.test.mock.declareMock
import kotlin.test.assertNotNull

class CharactersModuleTest : KoinTest {

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger()
        modules(charactersModule)
    }

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        // Your way to build a Mock here
        mockkClass(clazz)
    }

    @Test
    fun `should inject my components`() {
        declareMock<GetCharacterListUseCase>()
        declareMock<GetCharacterUseCase>()
        declareMock<ICharactersRepository>()
        declareMock<CharacterListViewModel>()

        assertNotNull(get<GetCharacterListUseCase>())

        assertNotNull(get<GetCharacterUseCase>())

        assertNotNull(get<ICharactersRepository>())

        assertNotNull(get<CharacterListViewModel>())

    }

}