package com.currency.converter.presentation.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.currency.converter.data.models.response.CurrenciesData
import com.currency.converter.data.repository.FakeCurrenciesRepository
import com.currency.converter.domain.usecase.FakeGetCurrenciesUseCase
import com.currency.converter.domain.usecases.GetCurrenciesUseCase
import com.currency.converter.getCurrenciesData
import com.currency.converter.launchOnIO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.Flow


@ExperimentalCoroutinesApi
class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel
    private lateinit var useCase: GetCurrenciesUseCase
    private lateinit var repository: FakeCurrenciesRepository


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        repository = FakeCurrenciesRepository(getCurrenciesData())
        useCase = GetCurrenciesUseCase(repository)
        viewModel = HomeViewModel(useCase, Dispatchers.IO)
    }

    @Test
    fun `get currencies success return true`() = runTest (UnconfinedTestDispatcher()) {
        launchOnIO {
            val data = viewModel.getCurrencies("USD")
            delay(1000)
            assertTrue(data!=null)
        }
    }



}