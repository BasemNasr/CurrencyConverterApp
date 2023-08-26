package com.currency.converter.data.repository

import com.currency.converter.data.models.BaseResponse
import com.currency.converter.data.models.response.GetCurrenciesResponse
import com.currency.converter.data.network.remote.NetworkResponse
import com.currency.converter.domain.repository.CurrenciesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.ResponseBody


class FakeCurrenciesRepository(
    private var data: GetCurrenciesResponse?=null,
    private var historicalResponse: ResponseBody?=null
) : CurrenciesRepository {

    override suspend fun getLatestExchangeRate(baseCurrency: String): Flow<NetworkResponse<GetCurrenciesResponse, BaseResponse>> {
        return flow {
            if (data != null) {
                emit(
                    NetworkResponse.Success(
                        data!!
                    )
                )
            } else {
                emit(
                    NetworkResponse.UnknownError(
                        Throwable("some thing wrong")
                    )
                )
            }
        }
    }

    override suspend fun getHistoricalExchangeRate(
        baseCurrency: String,
        dateFrom: String,
        dateTo: String
    ): Flow<NetworkResponse<ResponseBody, BaseResponse>> {
        return flow {
            if (historicalResponse != null) {
                emit(
                    NetworkResponse.Success(
                        historicalResponse!!
                    )
                )
            } else {
                emit(
                    NetworkResponse.UnknownError(
                        Throwable("some thing wrong")
                    )
                )
            }
        }
    }
}
