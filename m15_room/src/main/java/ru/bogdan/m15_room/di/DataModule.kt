package ru.bogdan.m15_room.di

import dagger.Binds
import dagger.Module
import ru.bogdan.m15_room.data.ApplicationRepositoryImpl
import ru.bogdan.m15_room.domain.ApplicationRepository

@Module
interface DataModule {
    @Binds
    fun bindApplicationRepository(impl: ApplicationRepositoryImpl): ApplicationRepository


}