package com.currency.converter.domain.repository


import com.currency.converter.data.models.BaseResponse
import com.currency.converter.data.models.response.GetCurrenciesResponse
import com.currency.converter.data.models.response.GetHistoricalResponse
import com.currency.converter.data.network.remote.NetworkResponse
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody

interface CurrenciesRepository {
    suspend fun getLatestExchangeRate(baseCurrency: String): Flow<NetworkResponse<GetCurrenciesResponse, BaseResponse>>
    suspend fun getHistoricalExchangeRate(
        baseCurrency: String,
        dateFrom: String,
        dateTo: String
    ): Flow<NetworkResponse<ResponseBody, BaseResponse>>


}