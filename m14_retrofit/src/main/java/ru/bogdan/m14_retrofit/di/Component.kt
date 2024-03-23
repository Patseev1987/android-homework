package ru.bogdan.m14_retrofit.di

import dagger.Component
import ru.bogdan.m14_retrofit.presentation.MainActivity
import ru.bogdan.m14_retrofit.presentation.MainViewModel
@ApplicationScope
@Component (modules = [DataModule::class, ViewModelModule::class])
interface Component {
    fun inject(mainActivity: MainActivity)

}