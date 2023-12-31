package com.currency.converter.di

import android.content.Context
import com.currency.converter.data.network.ServiceApi
import com.currency.converter.data.network.remote.NetworkResponseAdapterFactory
import com.currency.converter.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {




    @Provides
    @Singleton
    fun provideRetrofitClient(
        @ApplicationContext context: Context,
    ): ServiceApi {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val newRequest = chain.request().newBuilder()
                    .addHeader("Accept", "application/json")
                    .addHeader("Content-Type", "application/json")

                val mChain = chain.proceed(newRequest.build())
                mChain
            }.build()

        val baseUrl = BuildConfig.BASE_URL
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ServiceApi::class.java)
    }
}