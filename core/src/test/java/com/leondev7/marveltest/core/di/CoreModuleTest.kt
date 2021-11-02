package com.leondev7.marveltest.core.di

import io.mockk.mockkClass
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.get
import org.koin.test.mock.MockProviderRule
import org.koin.test.mock.declareMock
import kotlin.coroutines.CoroutineContext
import kotlin.test.assertNotNull

class CoreModuleTest : KoinTest {

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger()
        modules(coreModule)
    }

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        mockkClass(clazz)
    }

    @Test
    fun `should inject core components`() {
        declareMock<CoroutineContext>()

        assertNotNull(get<CoroutineContext>())
    }
}