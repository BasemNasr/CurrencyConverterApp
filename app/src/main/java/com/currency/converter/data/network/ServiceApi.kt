package com.currency.converter.data.network

import com.currency.converter.BuildConfig
import com.currency.converter.data.models.BaseResponse
import com.currency.converter.data.models.response.GetCurrenciesResponse
import com.currency.converter.data.models.response.GetHistoricalResponse
import com.currency.converter.data.network.remote.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Query


@JvmSuppressWildcards
interface ServiceApi {

    /**
     * @author General
     * */


    @GET(Urls.GET_LATEST_EXCHANGE_RATE)
    suspend fun getLatestExchangeRate(
        @Query("apikey") apikey: String?=BuildConfig.API_KEY,
        @Query("base_currency") baseCurrency: String? = "USD",
    ): NetworkResponse<GetCurrenciesResponse, BaseResponse>

    @GET(Urls.GET_HISTORICAL_EXCHANGE_RATE)
    suspend fun getHistoricalExchangeRate(
        @Query("apikey") apikey: String?=BuildConfig.API_KEY,
        @Query("base_currency") baseCurrency: String? = "USD",
        @Query("date_from") dateFrom: String? = null,
        @Query("date_to") dateTo: String? = null,
    ): NetworkResponse<GetHistoricalResponse, BaseResponse>

}


