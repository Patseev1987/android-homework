package com.example.m16_architecture.di

import com.example.m16_architecture.data.*
import com.example.m16_architecture.domain.ApplicationRepository
import com.example.m16_architecture.presentation.State
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.flow.MutableSharedFlow

@Module
interface DataModule {
    @ApplicationScope
    @Binds
    fun bindApiHelper(impl: ApiHelperImpl): ApiHelper

    @ApplicationScope
    @Binds
    fun bindApplicationRepository(impl: ApplicationRepositoryImpl): ApplicationRepository

    companion object {
        @ApplicationScope
        @Provides
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }

        @Provides
        fun provideLoadingFlow(): MutableSharedFlow<State> = MutableSharedFlow()

        @Provides
        fun provideFlow(): MutableSharedFlow<Unit> = MutableSharedFlow()
    }
}