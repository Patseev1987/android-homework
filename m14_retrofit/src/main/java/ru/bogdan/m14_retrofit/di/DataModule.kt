package ru.bogdan.m14_retrofit.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import ru.bogdan.m14_retrofit.data.ApiFactory
import ru.bogdan.m14_retrofit.data.ApiHelper
import ru.bogdan.m14_retrofit.data.ApiHelperImpl
import ru.bogdan.m14_retrofit.data.ApplicationRepositoryImpl
import ru.bogdan.m14_retrofit.domain.ApplicationRepository
import ru.bogdan.m14_retrofit.presentation.State

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
        fun provideApiService() = ApiFactory.apiService

        @Provides
        fun provideScope() = CoroutineScope(Dispatchers.IO)

        @Provides
        fun provideMutableSharedFlowWithStateInside() = MutableSharedFlow<State>()
        @Provides
        fun provideMutableSharedFlowWithUnitInside() = MutableSharedFlow<Unit>()
    }
}
