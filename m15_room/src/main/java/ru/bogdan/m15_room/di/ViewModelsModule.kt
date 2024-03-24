package ru.bogdan.m15_room.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import ru.bogdan.m15_room.presentation.MainViewModel

@Module
interface ViewModelsModule {
    @Binds
    @ViewModelKey(value = MainViewModel::class)
    @IntoMap
    fun bindViewModel(mainViewModel: MainViewModel): ViewModel
}