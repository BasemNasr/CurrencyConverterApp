package com.currency.converter.domain.usecase

import com.currency.converter.data.repository.FakeCurrenciesRepository

class FakeGetCurrenciesUseCase(
    private val fakeSongRepository: FakeCurrenciesRepository
) {
    suspend fun getCurrencies(baseCurrency: String) =
        fakeSongRepository.getLatestExchangeRate(baseCurrency)

}