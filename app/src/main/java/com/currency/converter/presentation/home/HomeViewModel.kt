package com.currency.converter.presentation.home

import com.currency.converter.base.BaseViewModel
import com.currency.converter.data.models.response.CurrenciesData
import com.currency.converter.data.network.remote.NetworkResponse
import com.currency.converter.di.IoDispatcher
import com.currency.converter.domain.usecases.GetLatestExchangeRateUseCase
import com.currency.converter.utils.AppUtils.getCurrencyValue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject
constructor(
    private val getLatestExchangeRateUseCase: GetLatestExchangeRateUseCase,
    @IoDispatcher private val io: CoroutineDispatcher
) : BaseViewModel() {

    private var selectedBase = "USD"
    private var selectedTo = "USD"

    private var _currencies: MutableStateFlow<CurrenciesData?> =
        MutableStateFlow(null)
    var currencies = _currencies.asStateFlow()


    fun getCurrencies(baseCurrency: String = selectedBase) {
        CoroutineScope(io).launch(handlerException) {
            launchIn = launch {
                getLatestExchangeRateUseCase.getCurrencies(baseCurrency).collect {
                    when (it) {
                        is NetworkResponse.Success -> {
                            _currencies.emit(it.body.data)
                        }

                        else -> {
                            _errorLoadingState.emit(it)
                        }
                    }
                }
            }
        }
    }

    fun updateSelectedBase(baseCurrency: String) {
        if (selectedBase != baseCurrency) {
            this.selectedBase = baseCurrency
            getCurrencies(baseCurrency)
        }
    }

    fun updateSelectedTo(selectedTo: String) {
        this.selectedTo = selectedTo
    }

    fun gettingCurrentToValue(userFromValue: Double): Double {
        currencies.value.let {
            val toValue = getCurrencyValue(selectedTo, it)
            return (userFromValue * (toValue ?: 0.0))
        }
        return 1.0
    }

    fun gettingCurrentFromValue(userToValue: Double): Double {
        currencies.value.let {
            val toValue = getCurrencyValue(selectedTo, it)
            return (userToValue / (toValue ?: 0.0))
        }
        return 1.0
    }

}