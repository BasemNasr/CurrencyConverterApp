package com.currency.conventer.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.currency.conventer.data.models.BaseResponse
import com.currency.conventer.data.network.remote.NetworkResponse
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.internal.synchronized
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {
    protected lateinit var launchIn: Job
    protected lateinit var secondIn: Job
    protected lateinit var mAllJob: Job
    private val mRetryList by lazy { ArrayList<AllStateEvent>() }

    protected val _errorLoadingState: MutableSharedFlow<NetworkResponse<Any, BaseResponse>> =
        MutableSharedFlow()
    val errorLoadingState = _errorLoadingState.asSharedFlow()

    val handlerException by lazy {
        CoroutineExceptionHandler { _, ex ->
            Log.e("Exception", "$ex")
            viewModelScope.launch { _errorLoadingState.emit(NetworkResponse.UnknownError(ex)) }
        }
    }

    abstract fun setStateEvent(state: AllStateEvent)
    open class AllStateEvent {
        object RetryRequest : AllStateEvent()
    }

    @OptIn(InternalCoroutinesApi::class)
    fun addEvent(event: AllStateEvent) {
        synchronized(ArrayList::class.java) {
            val newRequest = mRetryList.find { it == event }
            if (newRequest == null) mRetryList.add(event)
        }
    }

    @OptIn(InternalCoroutinesApi::class)
    fun clearEvent(event: AllStateEvent) {
        synchronized(ArrayList::class.java) {
            val newRequest = mRetryList.find { it == event }
            if (newRequest != null) mRetryList.remove(event)
        }
    }

    fun cancelAllRequests() {
        if (this::launchIn.isInitialized) launchIn.cancel()
        if (this::secondIn.isInitialized) secondIn.cancel()
    }

    fun checkSecondJob(): Boolean {
        return this::secondIn.isInitialized
    }

    fun checkFirstJob(): Boolean {
        return this::launchIn.isInitialized
    }

    override fun onCleared() {
        super.onCleared()
//        createPostLiveData.removeObserver(this)
        mRetryList.clear()
        if (::launchIn.isInitialized) launchIn.cancel()
        if (::secondIn.isInitialized) secondIn.cancel()
        if (::mAllJob.isInitialized) mAllJob.cancel()
    }
}