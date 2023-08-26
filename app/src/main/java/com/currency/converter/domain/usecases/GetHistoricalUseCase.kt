package com.currency.converter.domain.usecases

import com.currency.converter.domain.repository.CurrenciesRepository
import javax.inject.Inject

class GetHistoricalUseCase
@Inject
constructor(
    private val repo: CurrenciesRepository,
) {
    suspend fun getCurrencies(baseCurrency: String, from: String, to: String) =
        repo.getHistoricalExchangeRate(baseCurrency, from, to)
}