package ru.bogdan.m15_room.di

import android.app.Application
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import ru.bogdan.m15_room.data.WordMapper
import ru.bogdan.m15_room.data.database.WordsDatabase

@Module
class ProvidesModule {

    @Provides
    fun provideCoroutineScope() = CoroutineScope(Dispatchers.IO)

    @Provides
    fun provideDaoFromDatabase(application: Application) = WordsDatabase.getInstance(application).wordDao
}