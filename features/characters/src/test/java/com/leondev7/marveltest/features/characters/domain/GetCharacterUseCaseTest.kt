package com.leondev7.marveltest.features.characters.domain

import com.leondev7.marveltest.core.usecases.UseCaseResult
import com.leondev7.marveltest.features.characters.domain.entity.CharacterDomainEntity
import com.leondev7.marveltest.features.characters.domain.error.CharacterError
import com.leondev7.marveltest.features.characters.domain.repository.ICharactersRepository
import com.leondev7.marveltest.features.characters.domain.usecases.GetCharacterListUseCase
import com.leondev7.marveltest.features.characters.domain.usecases.GetCharacterUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs

class GetCharacterUseCaseTest : KoinTest {

    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private val getCharacterUseCase: GetCharacterUseCase by inject()
    private val repository: ICharactersRepository by inject()

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(
            module {
                factory { GetCharacterUseCase(get()) }
                single<ICharactersRepository> { mockk(relaxed = true) }
            })
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `should return success`() = testCoroutineDispatcher.runBlockingTest {
        val success = CharacterDomainEntity(0,"","","")
        //GIVEN
        coEvery {
            repository.getCharacterDetail(
                0,
            )
        }.returns(flowOf(success))

        //THEN
        assertEquals(
            UseCaseResult.Success(success),
            getCharacterUseCase(
                0,
            ).single()
        )
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `should return failure`() = testCoroutineDispatcher.runBlockingTest {
        // GIVEN
        coEvery {
            repository.getCharacterDetail(
                0
            )
        }.returns(flowOf(null))

        assertIs<UseCaseResult.Failure>(
            getCharacterUseCase(
                0
            ).single()
        )
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `should handle error`() = testCoroutineDispatcher.runBlockingTest {
        val failure = Exception("Error getting characters")
        // GIVEN
        coEvery {
            repository.getCharacterDetail(
                0
            )
        }.returns(flow { throw failure })

        assertEquals(
            UseCaseResult.Failure(failure),
            getCharacterUseCase(
                0
            ).single()
        )
    }
}