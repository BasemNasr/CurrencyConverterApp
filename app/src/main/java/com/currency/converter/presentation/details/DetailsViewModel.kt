package com.currency.converter.presentation.details

import androidx.lifecycle.viewModelScope
import com.currency.converter.base.BaseViewModel
import com.currency.converter.data.models.CurrencyModel
import com.currency.converter.data.models.response.CurrenciesData
import com.currency.converter.data.network.remote.NetworkResponse
import com.currency.converter.di.IoDispatcher
import com.currency.converter.domain.usecases.GetHistoricalUseCase
import com.currency.converter.domain.usecases.GetCurrenciesUseCase
import com.currency.converter.utils.AppUtils.getAllCurrenciesTitle
import com.currency.converter.utils.AppUtils.getCurrencyValue
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel
@Inject
constructor(
    private val getHistoricalUseCase: GetHistoricalUseCase,
    private val getLatestExchangeRateUseCase: GetCurrenciesUseCase,

    @IoDispatcher private val io: CoroutineDispatcher
) : BaseViewModel() {

    private var selectedBase = "USD"

    private var _historicalList: MutableStateFlow<ArrayList<CurrenciesData>> =
        MutableStateFlow(ArrayList())
    var historicalList = _historicalList.asStateFlow()

    private var _currencies: MutableStateFlow<ArrayList<CurrencyModel>?> =
        MutableStateFlow(ArrayList())
    var otherCurrencies = _currencies.asStateFlow()


    fun getHistoricalCurrencies(
        baseCurrency: String = selectedBase,
        dateFrom: String,
        dateTo: String,
        middleDate: String,
    ) {
        CoroutineScope(io).launch(handlerException) {
            launchIn = launch {
                getHistoricalUseCase.getCurrencies(baseCurrency, dateFrom, dateTo).collect {
                    when (it) {
                        is NetworkResponse.Success -> {
                            handleStringResultToListOfDays(
                                it.body.string(),
                                dateFrom,
                                dateTo,
                                middleDate
                            )
                        }
                        else -> {
                            _errorLoadingState.emit(it)
                        }
                    }
                }
            }
        }
    }

    private fun handleStringResultToListOfDays(
        response: String,
        dateFrom: String,
        dateTo: String,
        middleDate: String
    ) {
        var gson = Gson()

        var historicalList: ArrayList<CurrenciesData> = ArrayList()
        var responseObject = JSONObject(response)
        var dataObject = responseObject.getJSONObject("data")
        var firstDataString = dataObject.getJSONObject("$dateFrom").toString()
        var firstData = gson?.fromJson(firstDataString, CurrenciesData::class.java)
        firstData?.date = dateFrom


        var secondDateString = dataObject.getJSONObject("$middleDate").toString()
        var secondDate = gson?.fromJson(secondDateString, CurrenciesData::class.java)
        secondDate?.date = middleDate

        var thirdDateString = dataObject.getJSONObject("$dateTo").toString()
        var thirdDate = gson?.fromJson(thirdDateString, CurrenciesData::class.java)
        thirdDate?.date = dateTo


        thirdDate?.let {
            historicalList.add(thirdDate)
        }

        secondDate?.let {
            historicalList.add(secondDate)
        }

        firstData?.let {
            historicalList.add(firstData)
        }

        viewModelScope.launch {
            _historicalList.emit(historicalList)
        }

    }

    fun getCurrencies(baseCurrency: String = selectedBase) {
        CoroutineScope(io).launch(handlerException) {
            secondIn = launch {
                getLatestExchangeRateUseCase.getCurrencies(baseCurrency).collect {
                    when (it) {
                        is NetworkResponse.Success -> {
                            handleCurrenciesAsList(baseCurrency, it.body.data)
                        }

                        else -> {
                            _errorLoadingState.emit(it)
                        }
                    }
                }
            }
        }
    }


    private fun handleCurrenciesAsList(
        baseCurrency: String,
        data: CurrenciesData
    ) {
        var historicalList: ArrayList<CurrencyModel> = ArrayList()
        val currenciesArray = getAllCurrenciesTitle()
        for (currencyName in currenciesArray) {
            if (baseCurrency != currencyName) {
                historicalList.add(
                    CurrencyModel(
                        currencyName,
                        getCurrencyValue(currencyName, data)
                    )
                )
            }
        }

        viewModelScope.launch {
            _currencies.emit(historicalList)
        }

    }


}