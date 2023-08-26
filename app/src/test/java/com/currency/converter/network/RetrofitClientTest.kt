package com.currency.converter.network

import com.currency.converter.BuildConfig
import org.junit.Test
import retrofit2.Retrofit

class RetrofitClientTest {
    @Test
    fun testRetrofitInstance() {
        val instance: Retrofit = RetrofitClient().retrofit
        //Assert that, Retrofit's base url matches to our BASE_URL
        assert(instance.baseUrl().toUrl().toString() == BuildConfig.BASE_URL)
    }

}