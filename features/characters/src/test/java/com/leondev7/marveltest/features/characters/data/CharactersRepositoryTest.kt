package com.leondev7.marveltest.features.characters.data

import com.leondev7.marveltest.core.network.IMarvelApiClient
import com.leondev7.marveltest.features.characters.domain.entity.CharacterDomainEntity
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import kotlin.test.assertIs

class CharactersRepositoryTest : KoinTest {

    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private val apiClient: IMarvelApiClient by inject()
    private val repository: CharactersRepository by inject()

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(
            module {
                single<IMarvelApiClient> { mockk(relaxed = true) }
                single { CharactersRepository(get()) }
            }
        )
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `should return character list`() = testCoroutineDispatcher.runBlockingTest {
        //val repository = CharactersRepository(apiClient = apiClient)
        val response = TestResponse.characterResponse
        //GIVEN
        coEvery {
            apiClient.getMarvelCharacters(0, 0)
        }.returns(flowOf(response))

        //THEN
        assertIs<Array<CharacterDomainEntity>>(repository.getCharacters(0, 0).single())
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `should return character detail`() = testCoroutineDispatcher.runBlockingTest {
        //val repository = CharactersRepository(apiClient = apiClient)
        val response = TestResponse.characterResponse
        //GIVEN
        coEvery {
            apiClient.getCharacterDetail(
                0,
            )
        }.returns(flowOf(response))

        //THEN
        assertIs<CharacterDomainEntity>(repository.getCharacterDetail( 0).single())

    }

}