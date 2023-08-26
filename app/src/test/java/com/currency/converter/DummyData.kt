package com.currency.converter

import com.currency.converter.data.models.response.CurrenciesData
import com.currency.converter.data.models.response.GetCurrenciesResponse
import okhttp3.ResponseBody

fun getCurrenciesData(): GetCurrenciesResponse {
    val response = GetCurrenciesResponse(
        data = CurrenciesData(
            USD = 1.0,
            AUD = 1.0,
            BGN = 1.0,
            BRL = 1.0,
            CAD = 1.0,
            CHF = 1.0,
            CNY = 1.0,
            CZK = 1.0,
            DKK = 1.0,
            EUR = 1.0,
            GBP = 1.0,
            HKD = 1.0,
            HRK = 1.0,
            HUF = 1.0,
            IDR = 1.0,
            ILS = 1.0,
            INR = 1.0,
            ISK = 1.0,
            JPY = 1.0,
            KRW = 1.0,
            MXN = 1.0,
            MYR = 1.0,
            NOK = 1.0,
            NZD = 1.0,
            PHP = 1.0,
            PLN = 1.0,
            RON = 1.0,
            RUB = 1.0,
            SEK = 1.0,
            SGD = 1.0,
            THB = 1.0,
            TRY = 1.0,
            ZAR = 1.0
        )
    )

    return response

}


