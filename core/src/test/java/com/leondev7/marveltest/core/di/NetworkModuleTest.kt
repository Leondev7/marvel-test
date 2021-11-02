package com.leondev7.marveltest.core.di


import com.leondev7.marveltest.core.network.IMarvelApiClient
import com.leondev7.marveltest.core.network.MarvelApiClient
import com.leondev7.marveltest.core.network.di.networkModule
import io.mockk.mockkClass
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.get
import org.koin.test.mock.MockProviderRule
import org.koin.test.mock.declareMock
import kotlin.test.assertNotNull

class NetworkModuleTest : KoinTest {

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger()
        modules(networkModule)
    }

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        mockkClass(clazz)
    }

    @Test
    fun `should inject network components`() {
        declareMock<IMarvelApiClient>()
        assertNotNull(get<IMarvelApiClient>())
    }
}