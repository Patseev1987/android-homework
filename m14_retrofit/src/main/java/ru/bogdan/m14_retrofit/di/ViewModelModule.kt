package ru.bogdan.m14_retrofit.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.bogdan.m14_retrofit.presentation.MainViewModel

@Module
interface ViewModelModule {


    @IntoMap
    @ViewModelKey(MainViewModel::class)
    @Binds
    fun bindMainViewModel(mainViewModel: MainViewModel):ViewModel

}