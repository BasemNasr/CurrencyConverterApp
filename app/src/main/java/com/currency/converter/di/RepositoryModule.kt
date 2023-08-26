package com.currency.converter.di


import com.currency.converter.data.repository.CurrenciesRepositoryImpl
import com.currency.converter.domain.repository.CurrenciesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    @ViewModelScoped
    abstract fun bindCurrencyRepository(repository: CurrenciesRepositoryImpl): CurrenciesRepository


}