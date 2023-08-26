package com.currency.converter.data.repository

import com.currency.converter.data.network.ServiceApi
import com.currency.converter.data.network.remote.NetworkResponse
import com.currency.converter.domain.repository.CurrenciesRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CurrenciesRepositoryImpl @Inject
constructor(
    private val api: ServiceApi,
) : CurrenciesRepository {

    override suspend fun getLatestExchangeRate(baseCurrency: String) =
        flow {
            emit(NetworkResponse.Loading)
            when (val get =
                api.getLatestExchangeRate(baseCurrency = baseCurrency)) {
                is NetworkResponse.Success -> emit(NetworkResponse.Success(get.body))
                is NetworkResponse.ApiError -> emit(
                    NetworkResponse.ApiError(
                        get.body,
                        get.code
                    )
                )

                is NetworkResponse.NetworkError -> emit(NetworkResponse.NetworkError(get.error))
                else -> emit(NetworkResponse.UnknownError(Throwable(get.toString())))
            }
        }

    override suspend fun getHistoricalExchangeRate(
        baseCurrency: String,
        dateFrom: String,
        dateTo: String
    ) =
        flow {
            emit(NetworkResponse.Loading)
            when (val get =
                api.getHistoricalExchangeRate(
                    baseCurrency = baseCurrency,
                    dateFrom = dateFrom,
                    dateTo = dateTo
                )) {
                is NetworkResponse.Success -> emit(NetworkResponse.Success(get.body))
                is NetworkResponse.ApiError -> emit(
                    NetworkResponse.ApiError(
                        get.body,
                        get.code
                    )
                )

                is NetworkResponse.NetworkError -> emit(NetworkResponse.NetworkError(get.error))
                else -> emit(NetworkResponse.UnknownError(Throwable(get.toString())))
            }
        }
}