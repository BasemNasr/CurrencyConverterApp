package com.currency.converter.domain.usecases

import com.currency.converter.domain.repository.CurrenciesRepository
import javax.inject.Inject

class GetLatestExchangeRateUseCase
@Inject
constructor(
    private val repo: CurrenciesRepository,
) {
    suspend fun getCurrencies(baseCurrency: String) = repo.getLatestExchangeRate(baseCurrency)
}