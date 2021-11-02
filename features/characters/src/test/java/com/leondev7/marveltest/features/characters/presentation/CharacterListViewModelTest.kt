package com.leondev7.marveltest.features.characters.presentation

import com.leondev7.marveltest.core.usecases.UseCaseResult
import com.leondev7.marveltest.features.characters.domain.entity.CharacterDomainEntity
import com.leondev7.marveltest.features.characters.domain.usecases.GetCharacterListUseCase
import com.leondev7.marveltest.features.characters.presentation.list.CharacterListContract
import com.leondev7.marveltest.features.characters.presentation.list.CharacterListViewModel
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.leondev7.marveltest.features.characters.presentation.list.PAGE_INIT_ELEMENTS
import com.leondev7.marveltest.features.characters.presentation.list.PAGE_MAX_ELEMENTS
import com.leondev7.marveltest.features.characters.rules.MainCoroutineRule
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject

class CharacterListViewModelTest : KoinTest{

    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private val viewModel: CharacterListViewModel by inject()
    private val getCharacterListUseCase: GetCharacterListUseCase by inject()

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(
            module {
                single  <GetCharacterListUseCase> { mockk() }
                viewModel { CharacterListViewModel(get()) }
            }
        )
    }

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = MainCoroutineRule()


    @Test
    fun `state should change to success`() = testCoroutineDispatcher.runBlockingTest {
        //GIVEN
        val success = arrayOf(CharacterDomainEntity(0, "", "", ""))
        coEvery {
            getCharacterListUseCase(
                PAGE_MAX_ELEMENTS, PAGE_INIT_ELEMENTS
            )
        }.returns(flowOf(UseCaseResult.Success(success)))
        viewModel.uiState.observeForever {  }

        //WHEN
        viewModel.getCharactersFromScratch()

        //THEN
        assert(viewModel.uiState.value is CharacterListContract.State.Success)
    }


    @Test
    fun `state should change to empty`() = testCoroutineDispatcher.runBlockingTest {
        //GIVEN
        val success = emptyArray<CharacterDomainEntity>()
        coEvery {
            getCharacterListUseCase(
                PAGE_MAX_ELEMENTS, PAGE_INIT_ELEMENTS
            )
        }.returns(flowOf(UseCaseResult.Success(success)))
        viewModel.uiState.observeForever {}

        //WHEN
        viewModel.getCharactersFromScratch()

        //THEN
        assert(viewModel.uiState.value is CharacterListContract.State.Empty)
    }

    @Test
    fun `state should change to failure`() = testCoroutineDispatcher.runBlockingTest {
        //GIVEN
        val error = Throwable()
        coEvery {
            getCharacterListUseCase(
                PAGE_MAX_ELEMENTS, PAGE_INIT_ELEMENTS
            )
        }.returns(flowOf(UseCaseResult.Failure(error)))
        viewModel.uiState.observeForever {}

        //WHEN
        viewModel.getCharactersFromScratch()

        //THEN
        assert(viewModel.uiState.value is CharacterListContract.State.Failure)
    }



}