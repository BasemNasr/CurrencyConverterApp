package com.currency.conventer.data.network

import com.currency.conventer.data.models.BaseResponse
import com.currency.conventer.data.models.response.GetCurrenciesResponse
import com.currency.conventer.data.models.response.GetHistoricalResponse
import com.currency.conventer.data.network.remote.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Query


@JvmSuppressWildcards
interface ServiceApi {

    /**
     * @author General
     * */


    @GET(Urls.GET_LATEST_EXCHANGE_RATE)
    suspend fun getLatestExchangeRate(
        @Query("apikey") apikey: String?,
        @Query("base_currency") base_currency: String? = "USD",
    ): NetworkResponse<GetCurrenciesResponse, BaseResponse>

    @GET(Urls.GET_HISTORICAL_EXCHANGE_RATE)
    suspend fun getHistoricalExchangeRate(
        @Query("apikey") apikey: String?,
        @Query("base_currency") baseCurrency: String? = "USD",
        @Query("date_from") dateFrom: String? = null,
        @Query("date_to") dateTo: String? = null,
    ): NetworkResponse<GetHistoricalResponse, BaseResponse>

}


